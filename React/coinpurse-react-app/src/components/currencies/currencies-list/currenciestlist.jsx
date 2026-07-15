import React, { useState, useEffect } from "react";
import {CURRENCIES_LIST} from "../../../constants/constants.jsx";
import { Container } from "react-bootstrap";
import Table from "react-bootstrap/Table";

function CurrenciesList() {
    // Data fetch state
    const [data, setData] = useState([]);
    //const [loading, setLoading] = useState(true);
    //const [error, setError] = useState(null);

    useEffect(() => {
        fetch(CURRENCIES_LIST)
            .then(res => res.json())
            .then(r => {setData(r); console.log(r)})
    }, []);

    return (<Container>
        <Table striped>
            <thead>
            <tr>
                <th>#</th>
                <th>ISO Code</th>
                <th>Description</th>
                <th>Conversion ratio to EUR</th>
                <th>Creation date</th>
            </tr>
            </thead>
            <tbody>
            {data.map((currency) => (
                <tr key={currency.id}>
                    <td>{currency.id}</td>
                    <td>{currency.currency}</td>
                    <td>{currency.currencyDescription}</td>
                    <td>{currency.conversionRatioToEur}</td>
                    <td>{currency.createdon}</td>
                </tr>
            ))}

            </tbody>
        </Table>
    </Container>);
}

export default CurrenciesList;