import React, { useState, useEffect } from "react";
import PurseCard from "../purse-card/pursecard";
import CreatePurse from "../purse-modals/createpurse";
import axios from "axios";
import { PURSE } from "../../../constants/constants";
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
      <h1>Purses</h1>
      <Button variant="primary" onClick={handleShow}>Create purse</Button>
      <CreatePurse show = {show} handleClose = {handleClose} loadingprop={triggerRefresh} ></CreatePurse>

      <Stack direction="horizontal" gap={3}>
        {data.map((purse) => (
          <PurseCard purse={purse} key={purse.id}></PurseCard>
        ))}
      </Stack>
    </Container>
  );
}

export default PurseList;
