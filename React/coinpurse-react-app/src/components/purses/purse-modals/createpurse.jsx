import React, { useState, useEffect } from "react";
import PurseCard from "../purse-card/pursecard";
import axios from "axios";
import { PURSE } from "../../../constants/constants";
import Stack from "react-bootstrap/Stack";
import { Button, Modal } from "react-bootstrap";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Form from "react-bootstrap/Form";
import Spinner from 'react-bootstrap/Spinner';

function CreatePurse(props) {
    // Form data
    const [purseTitle, setPurseTitle] = useState('');
    const [purseCurrency, setPurseCurrency] = useState('');
    const [purseDescription, setPurseDescription] = useState('');
    const [loading, setLoading] = useState(false);
    const triggerRefresh = props.loadingprop;
    
    // Post data
    async function postData() {
        try {
            setLoading(true);
            const response = await axios.post(PURSE, {
                title: purseTitle,
                description: purseDescription,
                currency: purseCurrency
            });

            if (response.status === 201) {
                props.handleClose();
                setPurseTitle('');
                setPurseCurrency('');
                setPurseDescription('');
                setLoading(false);
                triggerRefresh();
            }
        } catch (error) {
            console.log(error);
        }

    }
    

  return (
    <Modal
      show={props.show}
      onHide={props.handleClose}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title>Create purse</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <FloatingLabel
          controlId="floatingTitle"
          label="Purse title"
          className="mb-3"
        >
          <Form.Control type="text" placeholder="Savings purse" name="title" value={purseTitle} onChange={ e => setPurseTitle(e.target.value) }/>
        </FloatingLabel>
        <FloatingLabel
          controlId="floatingDescription"
          label="Purse description"
          className="mb-3"
        >
          <Form.Control type="text" placeholder="For little savings" name="title" value={purseDescription} onChange={ e => setPurseDescription(e.target.value) }/>
        </FloatingLabel>
        <FloatingLabel controlId="floatingCurrency" label="Currency">
          <Form.Control type="text" placeholder="Currency" name="currency" value={purseCurrency} onChange={ e => setPurseCurrency(e.target.value) }/>
        </FloatingLabel>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={props.handleClose}>
          Close
        </Button>
        <Button variant="primary" onClick={() => postData()}>Save Changes
            <Spinner
                as="span"
                animation="border"
                size="sm"
                role="status"
                aria-hidden="true"
                hidden={!loading}
            />
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default CreatePurse;
