import {useEffect, useState} from "react";
import {ROLES} from "../../constants/constants.jsx";
import Form from 'react-bootstrap/Form';
import {Role} from "./role.jsx";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export function RoleList(){
    const [roles, setRoles] = useState([]);

    useEffect(() => {
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

         fetchRoles();
    }, []);

    return(
        <Container>
            <Row>
                <Col>
                    <Form>
                        {roles.length !== 0 && roles != null ?
                            roles.map(role =>
                                <Role name={role.name} key={role.id}></Role>
                            ) :
                            <Role name="New role" key={0}></Role>
                        }
                    </Form>
                </Col>
            </Row>
        </Container>
    );

}