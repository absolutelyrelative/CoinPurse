import {useState} from "react";
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import "./Role.css";
import Button from "react-bootstrap/Button";
import { FaSave } from 'react-icons/fa';
import {ROLES} from "../../constants/constants.jsx";
import Alert from 'react-bootstrap/Alert'

async function saveRole(object, setLoading, onSaveSuccess, setError){
    const {roleId, roleName} = object;
    const loading = true;
    let body;
    let method;
    setLoading(loading);

    // New role
    if(roleId === null){
        method = 'POST';
        body = JSON.stringify({
            name: roleName
        })
    } else {
        method = 'PUT';
        body = JSON.stringify({
            id: roleId,
            name: roleName
        })
    }

    try {
        const result = await fetch(ROLES, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            //body: JSON.stringify(object)
            body: body
        });

        if(result.ok) {
            setLoading(false);
            onSaveSuccess?.();
            setError(false);
        } else if(result.status === 400){
            setError(true);
        }
    } catch(e){
        console.log(e);
    }


}

export function Role(props){
    const [roleName, setRoleName] = useState(props.name);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);
    const roleId = props.id;
    const hasChanged = roleName !== props.name;

    return(
        <>
            <Row>
                <Col>
                    <Form>
                        <Form.Control plaintext value={roleName} defaultValue={roleName} onChange={
                                e => {setRoleName(e.target.value)}}
                            className={hasChanged ? "role-input modified" : "role-input"}
                        />

                    </Form>
                </Col>
                <Col>
                    <Button variant="success" hidden={!hasChanged} onClick={() =>
                        saveRole({roleId, roleName}, setLoading, props.onSaveSuccess, setError)}>
                        <FaSave className="me"/>
                    </Button>
                </Col>
            </Row>
            <Alert show={error} dismissible={true} variant="danger" onClose={() => setError(false)}>Error</Alert>
        </>
    );

}