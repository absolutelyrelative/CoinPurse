import {useState} from "react";
import Form from 'react-bootstrap/Form';
import "./Role.css";

export function Role(props){
    const [roleName, setRoleName] = useState(props.name);
    const hasChanged = roleName !== props.name;

    return(

        <Form.Control plaintext value={roleName} defaultValue={roleName} onChange={e => {setRoleName(e.target.value)}}
            className={hasChanged ? "role-input modified" : "role-input"}
        />

    );

}