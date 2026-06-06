import React, { useState, useEffect } from "react";
import { EVENT_SAVE } from "../../../constants/constants";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import axios from "axios";


function AddEvent({ purseId , sendDataToParent}) {
    var url = EVENT_SAVE;

    // Form data
    const [eventDate, setEventDate] = useState('');
    const [eventComment, setEventComment] = useState('');
    const [eventChange, setEventChange] = useState('');

    // Refresh table
    const [refreshTableData, setRefreshTableData] = useState(false);

    // Post data
    function postData() {
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
        })
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

    return (
        <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                <Form.Control type="textarea" placeholder="Date" name="date"
                              value={eventDate} onChange={ e => setEventDate(e.target.value) }/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Control as="textarea" placeholder="Comment" name="comment"
                              value={eventComment} onChange={ e => setEventComment(e.target.value) }/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Control as="textarea" placeholder="Change" name="change"
                              value={eventChange} onChange={ e => setEventChange(e.target.value) }/>
            </Form.Group>
            <Button variant="success" onClick={postData}>Add</Button>
        </Form>
    );
}

export default AddEvent;