import Button from "react-bootstrap/Button";
import Modal from 'react-bootstrap/Modal';
import EventModal from "./eventmodal.jsx";
import {useState} from "react";

function Event({event, requestRefresh, handleFireNotification}){
    const [showEditModal, setShowEditModal] = useState(false);
    const toggleShowEditModal = () => {setShowEditModal(show => !show);};
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const toggleShowDeleteModal = () => {setShowDeleteModal(show => !show);};

    return (
        <tr key={event.id}>
            <td>{event.id}</td>
            <td>{event.date}</td>
            <td>{event.comment}</td>
            <td>{event.delta}</td>
            <td>{event.finalvalue}</td>
            <td>
                <Button variant="warning" onClick={() => setShowEditModal(true)}>Edit</Button>
                <Button variant="danger" onClick={() => setShowDeleteModal(true)}>Delete</Button>
                <EventModal event={event} show={showEditModal} toggleModal={toggleShowEditModal} type="edit"
                    requestRefresh={requestRefresh} handleFireNotification={handleFireNotification}></EventModal>
                <EventModal event={event} show={showDeleteModal} toggleModal={toggleShowDeleteModal} type="delete"
                            requestRefresh={requestRefresh} handleFireNotification={handleFireNotification}></EventModal>
            </td>
        </tr>
    );
}

export default Event;