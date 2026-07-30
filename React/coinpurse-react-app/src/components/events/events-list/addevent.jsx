import React, { useState, useEffect } from "react";
import { EVENT_SAVE, CURRENCIES_LIST } from "../../../constants/constants";
import { NUMBER_FORMAT_ERROR } from "../../../constants/messages";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import axios from "axios";

function handleDate(inputDate, setDateFunction) {
    console.log("input:", inputDate);

    let isoDate = inputDate;

    if(inputDate == null || isNaN(Date.parse(inputDate))) {
        isoDate = new Date(Date.now()).toISOString();
    }

    console.log("setting:", isoDate);

    setDateFunction(isoDate);
}

function handleValidation(eventDate, eventComment, eventChange, setEventDate, setValidated) {
    if(eventChange == null || isNaN(parseFloat(eventChange))) {
        setValidated(false);
        return false;
    }
    handleDate(eventDate, setEventDate);
    return true;
}

async function fetchCurrencies(setCurrencies){
    try {
        const response = await fetch(CURRENCIES_LIST);

        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }

        const data = await response.json();
        setCurrencies(data);
    } catch (error) {
        console.error("Failed to fetch currencies:", error);
    }
};

function AddEvent({ purseId , sendDataToParent}) {
    var url = EVENT_SAVE;

    // Form data
    const [eventDate, setEventDate] = useState('');
    const [eventComment, setEventComment] = useState('');
    const [eventChange, setEventChange] = useState('');
    const [validated, setValidated] = useState(false);
    const [currencies, setCurrencies] = useState([]);
    const [selectedCurrency, setSelectedCurrency] = useState(null);

    // Refresh table
    const [refreshTableData, setRefreshTableData] = useState(false);

    // Refresh currencies
    useEffect(() => {
        fetchCurrencies(setCurrencies);
    }, []);

    // Post data
    function postData() {
        if(handleValidation(eventDate, eventComment, eventChange, setEventDate, setValidated)) {
            console.log("Posting with ", eventDate);
            axios.post(url, {
                comment: eventComment,
                date: new Date(eventDate),
                delta: eventChange,
                purse: {
                    id: purseId
                },
                currency: {
                    id: selectedCurrency
                }
            }, )
                .then(function (response) {
                    if(response.status == 200) {
                        // Refresh page
                        setRefreshTableData(true);
                        sendDataToParent(refreshTableData);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    }

    return (

        <Form validated={validated}>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                <Form.Control type="datetime-local" placeholder="Date" name="date"
                              value={eventDate} onChange={e => {
                    console.log("New value:", e.target.value);
                    setEventDate(e.target.value);
                }}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Control as="textarea" placeholder="Comment" name="comment"
                              value={eventComment} onChange={ e => setEventComment(e.target.value) }/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Control as="textarea" placeholder="Change" name="change" required
                              value={eventChange} onChange={ e => setEventChange(e.target.value) }
                              />
                <Form.Control.Feedback type="invalid">
                    {NUMBER_FORMAT_ERROR}
                </Form.Control.Feedback>
            </Form.Group>
            <Form.Select aria-label="Default select example" onChange={e => setSelectedCurrency(e.target.value)}>
                {currencies.map((currency) => (
                        <option value={currency.id}>{currency.currency}</option>
                ))}
            </Form.Select>
            <Button variant="success" onClick={postData}>Add</Button>
        </Form>
    );
}

export default AddEvent;