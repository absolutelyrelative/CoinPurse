import React, {useState, useEffect, useMemo} from "react";
import {CURRENCIES_UPDATE, CURRENCIES} from "../../../constants/constants.jsx";
import { Container } from "react-bootstrap";
import Table from "react-bootstrap/Table";
import { SlRefresh } from "react-icons/sl";
import Button from "react-bootstrap/Button";
import Spinner from 'react-bootstrap/Spinner';
import {MaterialReactTable, useMaterialReactTable} from "material-react-table";
import {Box, IconButton} from "@mui/material";
import {FaSave} from "react-icons/fa";

async function refreshCurrencies( setLoading ) {
    setLoading(true);
    await fetch(CURRENCIES_UPDATE, {
        method: "POST"
    })
        .catch( err => console.log(err))
        .finally( () => setLoading(false))
}

function CurrenciesList() {
    // Data fetch state
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const columns = useMemo( () => [
            {
                accessorKey: 'currency',
                header: 'Currency code',
                enableEditing: false
            },
            {
                accessorKey: 'currencyDescription',
                header: 'Currency name',
                enableEditing: false
            },
            {
                accessorKey: 'conversionRatioToEur',
                header: 'Conversion ratio to EUR',
                enableEditing: false
            },
            {
                accessorKey: 'updatedOn',
                header: 'Last refreshed',
                enableEditing: false
            }
        ], []
    );

    const table = useMaterialReactTable({
        columns,
        data: data,
        layoutMode: "grid",
        enableColumnOrdering: true, //enable some features
        enablePagination: false, //disable a default feature
        renderTopToolbarCustomActions: ({table}) => (
            <Button variant="success" onClick={() => {refreshCurrencies(setLoading)}}><SlRefresh />
                <Spinner
                    as="span"
                    animation="border"
                    size="sm"
                    role="status"
                    aria-hidden="true"
                    hidden={!loading}
                />
                <span className="visually-hidden">Loading...</span>
            </Button>
        )
    });

    useEffect(() => {
        fetch(CURRENCIES)
            .then(res => res.json())
            .then(r => {r.sort((a, b) => a.currency.localeCompare(b.currency)); setData(r)})
        .catch(err => setError(err))
            .finally(() => setLoading(false));
    }, [loading]);

    return (
        <Container>
            <MaterialReactTable table={table} />
        </Container>);
}

export default CurrenciesList;