import React, { useState, useEffect } from 'react';
import PurseCard from "../purse-card/pursecard";
import axios from "axios";
import {PURSE_LIST} from "../../../constants/constants";
import CardGroup from 'react-bootstrap/CardGroup';

const listPurses = PURSE_LIST;

function PurseList() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Make GET request to fetch data
        axios
            .get(listPurses)
            .then((response) => {
                setData(response.data);
                setLoading(false);
            })
            .catch((err) => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    console.log(data);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h1>Purses</h1>
            <CardGroup>
                {data.map((purse) => (
                    <PurseCard purse={purse}></PurseCard>
                ))}
            </CardGroup>
        </div>
    );
    
    
}

export default PurseList;