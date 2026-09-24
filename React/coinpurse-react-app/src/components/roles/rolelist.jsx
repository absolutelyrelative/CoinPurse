import {useEffect, useState} from "react";
import {ROLES} from "../../constants/constants.jsx";
import {Role} from "./role.jsx";
import Container from 'react-bootstrap/Container';

export function RoleList(){
    const [roles, setRoles] = useState([]);

    const fetchRoles = async () => {
        try{
            const response = await fetch(ROLES);

            if (response.ok) {
                setRoles(await response.json());
            }
        } catch(err){
            console.error(err);
        }
    }

    useEffect(() => {
         fetchRoles();
    }, []);

    return(
        <Container>

            {roles.map(role =>
                    <Role name={role.name} key={role.id} id={role.id} onSaveSuccess={() => fetchRoles()}></Role>
                )}
            <Role name="New role" key={0} id={null} onSaveSuccess={() => fetchRoles()}></Role>

        </Container>
    );

}