import Button from "react-bootstrap/Button";
import Modal from 'react-bootstrap/Modal';
import {useState} from "react";
import EventEditModal from "./eventeditmodal.jsx";
import EventDeleteModal from "./eventdeletemodal.jsx";

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
                <EventEditModal event={event} show={showEditModal} toggleModal={toggleShowEditModal} type="edit"
                    requestRefresh={requestRefresh} handleFireNotification={handleFireNotification}></EventEditModal>
                <EventDeleteModal event={event} show={showDeleteModal} toggleModal={toggleShowDeleteModal} type="delete"
                            requestRefresh={requestRefresh} handleFireNotification={handleFireNotification}></EventDeleteModal>
            </td>
        </tr>
    );
}

export default Event;