import React from 'react'
import { Table } from "react-bootstrap"
//import { isEmpty, map } from "lodash"
 
export default function ListAddress(props) {    
    

    // console.log("LISTA ADDRESS ",props.addressData)

    return (
        <div>
            <Table striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Address</th>
                        <th>Type</th>
                        <th>Country</th>
                        <th>State</th>
                        <th>City</th>
                    </tr>
                </thead>
                <tbody>
                     {/* {
                        map(props.addressData, (address) =>(
                            <tr key={address.id}>
                                <td>{address.address}</td>
                                <td>{address.type}</td>
                                <td>{address.country}</td>
                                <td>{address.province}</td>
                                <td>{address.city}</td>
                            </tr>
                        ))
                    }                    */}
                    {/* {                        
                        
                        props.addressData.map(address =>(
                            <tr>
                                <td>{address.address}</td>
                                <td>{address.type}</td>
                                <td>{address.country}</td>
                                <td>{address.province}</td>
                                <td>{address.city}</td>
                            </tr>
                        )) 
                    } */}
                </tbody>
            </Table>
        </div>
    )
}
