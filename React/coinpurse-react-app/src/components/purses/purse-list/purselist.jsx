import React, { useState, useEffect } from "react";
import PurseCard from "../purse-card/pursecard";
import CreatePurse from "../purse-modals/createpurse";
import axios from "axios";
import { PURSE } from "../../../constants/constants";
import Stack from "react-bootstrap/Stack";
import { Button, Container, Row, Col } from "react-bootstrap";
import Spinner from 'react-bootstrap/Spinner';

function PurseList() {

  // Data fetch state
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Modal state
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  const [refreshTrigger, setRefreshTrigger] = useState(0);
  const triggerRefresh = () => setRefreshTrigger((prev) => prev + 1);

  // Fetch data
  useEffect(() => {
    // Make GET request to fetch data
    axios
      .get(PURSE)
      .then((response) => {
        setData(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, [refreshTrigger]);

  // Handle errors on fetch
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <Container>
          <Row xs="auto" className="mb-4">
              <Col>
                <h1>Actions</h1>
              </Col>
              <Col>
                <Button variant="primary" onClick={handleShow}>Create purse</Button>
                <CreatePurse show = {show} handleClose = {handleClose} loadingprop={triggerRefresh} ></CreatePurse>
                  <Spinner
                      as="span"
                      animation="border"
                      size="sm"
                      role="status"
                      aria-hidden="true"
                      hidden={!loading}
                  />
              </Col>
          </Row>
            <Row xs={1} md={3} lg={6}>
                    {data.map((purse) => (
                        <Col key={purse.id} >
                        <PurseCard purse={purse} key={purse.id}></PurseCard>
                        </Col>
                    ))}
        </Row>
    </Container>
  );
}

export default PurseList;
