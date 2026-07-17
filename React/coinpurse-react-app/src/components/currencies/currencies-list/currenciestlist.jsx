import React, { useState, useEffect } from "react";
import {CURRENCIES_LIST, CURRENCIES_UPDATE} from "../../../constants/constants.jsx";
import { Container } from "react-bootstrap";
import Table from "react-bootstrap/Table";
import { SlRefresh } from "react-icons/sl";
import Button from "react-bootstrap/Button";

function refreshCurrencies( setLoading ) {
    fetch(CURRENCIES_UPDATE, {
        method: "POST"
    })
        .catch( err => console.log(err))
        .finally( () => setLoading(true))
}

function CurrenciesList() {
    // Data fetch state
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(CURRENCIES_LIST)
            .then(res => res.json())
            .then(r => {r.sort((a, b) => a.currency.localeCompare(b.currency)); setData(r)})
        .catch(err => setError(err))
            .finally(() => setLoading(false));
    }, [loading]);

    return (<Container>
        <Table striped>
            <thead>
            <tr>
                <th>#</th>
                <th>ISO Code</th>
                <th>Description</th>
                <th>Conversion ratio to EUR</th>
                <th>Update date</th>
                <th>
                    <Button variant="success" onClick={() => {refreshCurrencies(setLoading)}}><SlRefresh /></Button>
                </th>
            </tr>
            </thead>
            {loading == true ? null : (<tbody>
            {
                data.map((currency) => (
                    <tr key={currency.id}>
                        <td>{currency.id}</td>
                        <td>{currency.currency}</td>
                        <td>{currency.currencyDescription}</td>
                        <td>{currency.conversionRatioToEur}</td>
                        <td>{currency.updatedOn}</td>
                    </tr>
                ))
            }

            </tbody>) }

        </Table>
    </Container>);
}

export default CurrenciesList;