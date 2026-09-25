import {useEffect, useState} from "react";
import {ROLES} from "../../constants/constants.jsx";
import {Role} from "./role.jsx";
import Container from 'react-bootstrap/Container';
import { useMemo } from 'react';
import {
    MaterialReactTable,
    useMaterialReactTable,
} from 'material-react-table';
import {
    Edit as EditIcon,
    Delete as DeleteIcon
} from '@mui/icons-material';
import {Box, IconButton} from "@mui/material";
import {FaSave} from "react-icons/fa";

export function RoleList(){
    const [roles, setRoles] = useState([]);
    // Track pending row updates: rowId: { name: 'New Name' }
    const [pendingEdits, setPendingEdits] = useState({});

    const handleSaveRow = async (rowId) => {
        const idToUpdate = rowId; // id
        const updatedName = pendingEdits[rowId]; // new name
        let body;
        let method;

        if(!updatedName && idToUpdate != null) return;
        // New role
        if(idToUpdate === null){
            method = 'POST';
            body = JSON.stringify({
                name: updatedName
            })
        } else {
            method = 'PUT';
            body = JSON.stringify({
                id: idToUpdate,
                name: updatedName
            })
        }

        try{
            const result = await fetch(ROLES, {
                method: method,
                body: body,
                headers: {
                    'Content-Type': 'application/json',
                }
            })

            if(result.ok){
                setPendingEdits((prevState) => ({...prevState, [idToUpdate]: undefined}))
                await fetchRoles();
            } else if(result.status === 400){
                console.log("bad request");
            }
        } catch(e) {
            console.log(e);
        }
    }

    const columns = useMemo( () => [
            {
                accessorKey: 'name',
                header: 'Role name',
                enableEditing: true,
                muiEditTextFieldProps: ({ cell, row, table }) => ({
                    onChange: (e) => {
                        const newValue = e.target.value;
                        const originalValue = row.original.name;

                        setPendingEdits(
                            prevState => {
                                return {
                                    ...prevState,
                                    [row.original.id]: newValue !== originalValue ? newValue : undefined
                                }
                            }
                        )
                    }
                }),
            }
        ], []
    );

    const table = useMaterialReactTable({
        columns,
        data: roles,
        layoutMode: "grid",
        enableRowActions: true,
        renderRowActions: ({ row, table }) => {
            // if row.id has no edits, object {} is empty and truthiness is false
            //  otherwise it is true
            const isRowEdited = !!pendingEdits[row.original.id];

            return (
                <Box>
                    <IconButton onClick={() => console.info('Delete')}>
                        <DeleteIcon/>
                    </IconButton>
                    <IconButton hidden={!isRowEdited} onClick={(e) => handleSaveRow(row.original.id)}>
                        <FaSave/>
                    </IconButton>
                </Box>);
        },
        enableColumnOrdering: true, //enable some features
        enableRowSelection: true,
        enablePagination: false, //disable a default feature
        editDisplayMode: 'cell',
        enableEditing: true
    });

    const fetchRoles = async () => {
        try{
            const response = await fetch(ROLES);

            if (response.ok) {
                setRoles(await response.json());
            }
        } catch(err){
            console.error(err);
        }
    }

    useEffect(() => {
         fetchRoles();
    }, []);


    return(
        <Container>
            <MaterialReactTable table={table} />
        </Container>
    )

}