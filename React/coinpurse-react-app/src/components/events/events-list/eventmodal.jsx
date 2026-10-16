import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import {useState} from "react";
import Notification from "../../utility/notification.jsx";
import {EVENTS} from "../../../constants/constants.jsx";

function EventModal({event, show, toggleModal, type= "edit", showExplicitCloseButton = false, requestRefresh,
                        handleFireNotification}) {
    let message = "";
    let body = "";
    let actionButton;

    const handleDelete = async (event) => {
        try {
            const result = await fetch(`${EVENTS}\\${event.id}`, {
                method: "DELETE"
            });

            if(result.ok){
                requestRefresh();
                handleFireNotification("Element deleted", false);
            } else {
                handleFireNotification("Error deleting element",true);
            }
        } catch (e) {
            handleFireNotification("Error deleting element", true);
        }
    }

    switch(type){
        case "edit":
            message = "Edit event";
            actionButton = <Button variant="warning">Edit</Button>;
            break;
        case "delete":
            message = "Delete event";
            body = <p> Are you sure you want to delete this event? <br/>
                <i> Comment: </i>{event.comment} <br/>
                <i>Change: </i> {event.delta}
            </p>;
            actionButton = <Button variant="danger" onClick={() => handleDelete(event)}>Delete</Button>;
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
                    {body}
            </Modal.Body>
            <Modal.Footer>
                {showExplicitCloseButton ? <Button onClick={toggleModal}>Close</Button> : ""}
                {actionButton}
            </Modal.Footer>
        </Modal>
    );
}

export default EventModal;