import {useState} from "react";
import Toast from 'react-bootstrap/Toast';

function Notification({message = "", isError = false, showInitially = false}) {
    const [show, setShow] = useState(showInitially);
    const toggleShow = () => {setShow(show => !show);};

    return(
        <Toast show={show} onClose={toggleShow}>
            <Toast.Header>
                <img
                    src="holder.js/20x20?text=%20"
                    className="rounded me-2"
                    alt=""
                />
                <strong className="me-auto">{isError ? "Error" : "Alert"}</strong>
            </Toast.Header>
            <Toast.Body>{message}</Toast.Body>
        </Toast>
    );
}

export default Notification;