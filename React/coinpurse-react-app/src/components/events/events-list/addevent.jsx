import React, { useState, useEffect } from "react";
import {CURRENCIES, EVENTS } from "../../../constants/constants";
import { NUMBER_FORMAT_ERROR } from "../../../constants/messages";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import axios from "axios";
import Notification from "../../utility/notification.jsx";

function getProcessedDate(inputDate){
    let isoDate = inputDate;

    if(inputDate == null || isNaN(Date.parse(inputDate))) {
        isoDate = new Date(Date.now()).toISOString();
    }

    return isoDate;
}

function handleValidation(eventDate, eventComment, eventChange, setValidated) {
    if(eventChange == null || isNaN(parseFloat(eventChange))) {
        setValidated(false);
        return false;
    }
    return true;
}

async function fetchCurrencies(setCurrencies, setSelectedCurrency){
    try {
        const response = await fetch(CURRENCIES);

        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }

        const data = await response.json();
        setCurrencies(data);

        // Set default currency
        if(data.length > 0){
            setSelectedCurrency(data[0].id);
        }
    } catch (error) {
        console.error("Failed to fetch currencies:", error);
    }
};

function AddEvent({ purseId, requestRefresh, handleFireNotification}) {
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
        fetchCurrencies(setCurrencies, setSelectedCurrency);
    }, []);

    // Post data
    async function postData() {
        const date = getProcessedDate(eventDate);

        if(handleValidation(eventDate, eventComment, eventChange, setValidated)) {
            try {
                const result = await axios.post(url, {
                    comment: eventComment,
                    date: date,
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
                    handleFireNotification("Event added.", false);
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
                    <Notification message={NUMBER_FORMAT_ERROR} showInitially={true} isError={true}></Notification>
                </Form.Control.Feedback>
            </Form.Group>
            <Form.Select aria-label="Default select example" onChange={(e) => setSelectedCurrency(e.target.value)}>
                {currencies.map((currency) => (
                        <option key={currency.id} value={currency.id}>{currency.currency}</option>
                ))}
            </Form.Select>
            <Button variant="success" onClick={postData}>Add</Button>
        </Form>
    );
}

export default AddEvent;