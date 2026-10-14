import React, { useState, useEffect } from "react";
import {CURRENCIES, EVENTS } from "../../../constants/constants";
import { NUMBER_FORMAT_ERROR } from "../../../constants/messages";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import axios from "axios";

function handleDate(inputDate, setDateFunction) {
    let isoDate = inputDate;

    if(inputDate == null || isNaN(Date.parse(inputDate))) {
        isoDate = new Date(Date.now()).toISOString();
    }

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
        const response = await fetch(CURRENCIES);

        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }

        const data = await response.json();
        setCurrencies(data);
    } catch (error) {
        console.error("Failed to fetch currencies:", error);
    }
};

function AddEvent({ purseId, requestRefresh}) {
    var url = EVENTS;

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
    async function postData() {
        if(handleValidation(eventDate, eventComment, eventChange, setEventDate, setValidated)) {
            try {
                const result = await axios.post(url, {
                    comment: eventComment,
                    date: new Date(eventDate),
                    delta: eventChange,
                    purseId: purseId,
                    currency: {
                        id: selectedCurrency
                    }
                },);

                if(result.status === 201) {
                    // Refresh page
                    requestRefresh();
                    setRefreshTableData(true);
                }
            } catch (error) {
                console.error("Failed to post data:", error);
            }


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
                        <option key={currency.id} value={currency.id}>{currency.currency}</option>
                ))}
            </Form.Select>
            <Button variant="success" onClick={postData}>Add</Button>
        </Form>
    );
}

export default AddEvent;