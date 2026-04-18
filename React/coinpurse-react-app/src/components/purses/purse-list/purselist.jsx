import React, { useState, useEffect } from "react";
import PurseCard from "../purse-card/pursecard";
import CreatePurse from "../purse-modals/createpurse";
import axios from "axios";
import { PURSE_LIST } from "../../../constants/constants";
import Stack from "react-bootstrap/Stack";
import { Button, Container } from "react-bootstrap";

function PurseList() {

  // Data fetch state
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Modal state
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  // Fetch data
  useEffect(() => {
    // Make GET request to fetch data
    axios
      .get(PURSE_LIST)
      .then((response) => {
        setData(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  // Handle errors on fetch
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <Container>
      <h1>Purses</h1>
      <Button variant="primary" onClick={handleShow}>Create purse</Button>
      <CreatePurse show = {show} handleClose = {handleClose} ></CreatePurse>

      <Stack direction="horizontal" gap={3}>
        {data.map((purse) => (
          <PurseCard purse={purse} key={purse.id}></PurseCard>
        ))}
      </Stack>
    </Container>
  );
}

export default PurseList;
