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

    // Fetch data
    useEffect(() => {
            axios
                .get(url)
                .then((response) => {
                    const res = response.data;

                    let parsedArray = [];
                    //if (Array.isArray(res)) {
                    //    parsedArray = res;
                    //} else if (res && typeof res === "object") {
                    //    // Adjust property names (res.events or res.data) if wrapped, otherwise convert object values
                    //    parsedArray = res.events || res.data || Object.values(res);
                    //}
                    //setData(parsedArray);
                    setData(res);
                    setLoading(false);
                })
                .catch((err) => {
                    setError(err.message);
                    setLoading(false);
                })
                .finally(() =>
                console.log(data))
        }, [loading]
    );


    debugger


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
          <AddEvent purseId={params.id} ></AddEvent>
    </Container>
    );
}

export default EventsList;