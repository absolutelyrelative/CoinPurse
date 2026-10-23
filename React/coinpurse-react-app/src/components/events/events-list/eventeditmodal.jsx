import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import React, {useState} from "react";
import Notification from "../../utility/notification.jsx";
import {EVENTS} from "../../../constants/constants.jsx";
import Table from "react-bootstrap/Table";

function EventEditModal({event, show, toggleModal, showExplicitCloseButton = false, requestRefresh,
                        handleFireNotification}) {

    const [formData, setFormData] = useState({date: '', comment: '', delta: ''});

    function handleChange({value, name}) {
        setFormData((prev) => ({...prev, [name]: value}));
    }

    return(
        <Modal
            show={show}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
            onHide={toggleModal}
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Edit event
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form id="entry-form" />
                <Table striped>
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Comment</th>
                        <th>Change</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>{event.date}</th>
                        <th>{event.comment}</th>
                        <th>{event.delta}</th>
                    </tr>
                    </tbody></Table>
                <center><br/>↓<br/></center>
                <Table striped>
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Comment</th>
                        <th>Change</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>
                            <input id="date" name="date" value={formData.date} type="datetime-local"
                                   onChange={e => handleChange(e.target)} >
                            </input>
                        </th>
                        <th>
                            <input id="comment" name="comment" value={formData.comment} type="text"
                                   onChange={e => handleChange(e.target)}>
                            </input>
                        </th>
                        <th>
                            <input id="delta" name="delta" value={formData.date} type="number"
                                   onChange={e => handleChange(e.target)}/>
                        </th>
                    </tr>
                    </tbody></Table>
            </Modal.Body>
            <Modal.Footer>
                {showExplicitCloseButton ? <Button onClick={toggleModal}>Close</Button> : ""}
                <Button variant="warning">Edit</Button>
            </Modal.Footer>
        </Modal>
    );
}

export default EventEditModal;