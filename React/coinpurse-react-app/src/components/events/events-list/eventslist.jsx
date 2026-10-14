import React, { useState, useEffect } from "react";
import axios from "axios";
import {EVENTS, EVENTS_LIST_BY_PURSE} from "../../../constants/constants";
import Table from 'react-bootstrap/Table';
import { useParams } from 'react-router-dom';
import { Container } from "react-bootstrap";
import EventsGraph from "./eventsgraph";
import AddEvent from "./addevent.jsx";
import Spinner from 'react-bootstrap/Spinner';
import Notification from "../../utility/notification.jsx";
import Event from "./event.jsx";

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
        }, [refreshData]
    );


    // Handle errors on fetch
    if (loading) return <div><Spinner/></div>;
    if (error) return <div><Notification message={"Error loading event list."}></Notification></div>;

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
            <Event event={event} key={event.id}></Event>
        ))}

      </tbody>
    </Table>
          <AddEvent purseId={params.id} requestRefresh={requestRefresh}></AddEvent>
    </Container>
    );
}

export default EventsList;