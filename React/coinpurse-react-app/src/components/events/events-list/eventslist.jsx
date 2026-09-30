import React, { useState, useEffect } from "react";
import axios from "axios";
import {EVENTS, EVENTS_LIST_BY_PURSE} from "../../../constants/constants";
import Table from 'react-bootstrap/Table';
import { useParams } from 'react-router-dom';
import { Container } from "react-bootstrap";
import EventsGraph from "./eventsgraph";
import Button from "react-bootstrap/Button";
import AddEvent from "./addevent.jsx";

function EventsList() {
    let params = useParams();
    let url = EVENTS_LIST_BY_PURSE + params.id;

    // Data fetch state
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [refreshData, setRefreshData] = useState(0);
    const requestRefresh = () =>(setRefreshData((prev) => prev + 1));

    // Fetch data
    useEffect(() => {
            axios
                .get(url)
                .then((response) => {
                    const res = response.data;
                    setData(res);
                    setLoading(false);
                })
                .catch((err) => {
                    setError(err.message);
                    setLoading(false);
                })
                .finally(() =>
                console.log(data))
        }, [loading, refreshData]
    );


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
          <AddEvent purseId={params.id} requestRefresh={requestRefresh}></AddEvent>
    </Container>
    );
}

export default EventsList;