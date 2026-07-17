import React, { useState, useEffect } from "react";
import { EVENT_SAVE } from "../../../constants/constants";
import { NUMBER_FORMAT_ERROR } from "../../../constants/messages";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import axios from "axios";

function handleDate(inputDate, setDateFunction) {
    let isoDate;
    if(inputDate == null || isNaN(Date.parse(inputDate))) {
        isoDate = new Date(Date.now()).toISOString();
    }
    setDateFunction(isoDate);
}

function handleValidation(eventDate, eventComment, eventChange, setValidated) {
    if(eventChange == null || isNaN(parseFloat(eventChange))) {
        setValidated(false);
        return false;
    }
    return true;
}

function AddEvent({ purseId , sendDataToParent}) {
    var url = EVENT_SAVE;

    // Form data
    const [eventDate, setEventDate] = useState('');
    const [eventComment, setEventComment] = useState('');
    const [eventChange, setEventChange] = useState('');
    const [validated, setValidated] = useState(false);

    // Refresh table
    const [refreshTableData, setRefreshTableData] = useState(false);

    // Post data
    function postData() {
        if(handleValidation(eventDate, eventComment, eventChange, setValidated)) {
            axios.post(url, {
                comment: eventComment,
                    date: new Date(eventDate),
                delta: eventChange,
                purse: {
                    id: purseId
                },
                currency: {
                    id: 1
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
                <Form.Control type="textarea" placeholder="Date" name="date"
                              value={eventDate} onChange={ e =>
                                            handleDate(e.target.value, setEventDate ) }/>
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
            <Button variant="success" onClick={postData}>Add</Button>
        </Form>
    );
}

export default AddEvent;