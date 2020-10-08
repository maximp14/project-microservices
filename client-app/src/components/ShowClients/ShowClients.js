import React, { useState, useEffect } from 'react'
import {Table} from "react-bootstrap"
import "./ShowClients.css"

export default function ShowClients() {

    const [client, setClient] = useState([])

    useEffect(() => {
        getClients()
      }, [])


    const getClients = async () =>{
        const data = await fetch("http://localhost:8080/api/clients")
        const clients = await data.json()
        setClient(clients)
    }

    return (
        <div className="client-table">
            <Table striped bordered hover size="sm">
                <thead>
                    <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>CUIT</th>
                    <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        client.length > 0 ?
                        client.map(c =>(
                            <tr key={c.id}>
                                <td>{c.id}</td>
                                <td>{c.name}</td>
                                <td>{c.cuit}</td>
                                <td>{c.description}</td>   
                            </tr>
                        )) : (
                            <tr>
                                <td colSpan={3}>No users</td>
                            </tr>
                        )
                    }
                </tbody>
            </Table>
        </div>
    )
}
