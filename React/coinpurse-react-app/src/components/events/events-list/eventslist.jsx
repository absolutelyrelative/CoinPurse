import React, { useState, useEffect } from "react";
import axios from "axios";
import { EVENTS_LIST_BY_PURSE } from "../../../constants/constants";
import Table from 'react-bootstrap/Table';
import { useParams } from 'react-router-dom';
import { Container } from "react-bootstrap";
import EventsGraph from "./eventsgraph";
import Button from "react-bootstrap/Button";
import AddEvent from "./addevent.jsx";

// Fetch data function
const getData = async(url, setError, setLoading, setDataFromChild, setData) => {
  // Make GET request to fetch data
    axios
    .get(url)
    .then((response) => {
        console.log("refreshed");
        setData(response.data);
        setLoading(false);
        setDataFromChild(false);
    })
    .catch((err) => {
        setError(err.message);
        setLoading(false);
    });
};

function EventsList() {
    let params = useParams();
    var url = EVENTS_LIST_BY_PURSE + params.id;

    // Refresh table on trigger
    const [dataFromChild, setDataFromChild] = useState(false);

    async function handleDataFromChild(data) {
      setDataFromChild(data);
      await getData(url, setError, setLoading, setDataFromChild, setData);
      setDataFromChild(false);
    }

    // Data fetch state
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch data
    useEffect(() => {getData(url, setError, setLoading, setDataFromChild, setData)}, [dataFromChild, loading]);

    

    // Handle errors on fetch
    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
      <Container>
      <EventsGraph data={data}></EventsGraph>
    <Table striped>
      <thead>
        <tr>
          <th>#</th>
          <th>Date</th>
          <th>Comment</th>
          <th>Change</th>
          <th>Total</th>
            <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {data.map((event) => (
          <tr key={event.id}>
            <td>{event.id}</td>
            <td>{event.date}</td>
            <td>{event.comment}</td>
            <td>{event.delta}</td>
            <td>{event.finalvalue}</td>
              <td>
                  <Button variant="warning">Edit</Button>
                  <Button variant="danger">Delete</Button>
              </td>
        </tr>
        ))}

      </tbody>
    </Table>
          <AddEvent purseId={params.id} sendDataToParent={handleDataFromChild}></AddEvent>
    </Container>
    );
}

export default EventsList;