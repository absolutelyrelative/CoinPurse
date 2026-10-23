import {useState} from "react";
import Toast from 'react-bootstrap/Toast';
import {ToastContainer} from "react-bootstrap";

function Notification({message = "", isError = false, showInitially = false}) {
    const [show, setShow] = useState(showInitially);
    const toggleShow = () => {setShow(show => !show);};

    if(show === false) return "";

    return(
        <div
            style={{
                position: 'fixed',
                top: 0,
                left: 0,
                width: '100vw',
                height: '100vh',
                backgroundColor: 'rgba(0, 0, 0, 0.4)',
                zIndex: 9998

            }}
        >
        <ToastContainer
            position="middle-center"
            className="p-3"
            style={{ position: 'fixed', zIndex: 9999 }}
        >
        <Toast show={show} onClose={toggleShow} bg={isError ? "danger" : "success"}>
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
        </ToastContainer>
    </div>);
}

export default Notification;