import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import {useState} from "react";
import Notification from "../../utility/notification.jsx";

function EventModal({event, show, toggleModal, type= "edit", showExplicitCloseButton = false}) {
    let message = "";
    let actionButton;

    switch(type){
        case "edit":
            message = "Edit event";
            actionButton = <Button variant="warning">Edit</Button>;
            break;
        case "delete":
            message = "Delete event";
            actionButton = <Button variant="danger">Delete</Button>;
            break;
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
                    {message}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>
                    {event.id}
                </p>
            </Modal.Body>
            <Modal.Footer>
                {showExplicitCloseButton ? <Button onClick={toggleModal}>Close</Button> : ""}
                {actionButton}
            </Modal.Footer>
        </Modal>
    );
}

export default EventModal;