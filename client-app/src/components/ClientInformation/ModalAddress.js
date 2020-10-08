import React, { useState } from 'react'
import { Modal } from "react-bootstrap"
import Address from './Address';


export default function ClientInformation(props) {

    const [show, setShow] = useState(true);    

    const handleClose = () => {
        setShow(!show)
        props.changeModalStatus(false)        
    }
    

    return (
        <div>           
            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}                
                >
                <Modal.Header closeButton>
                <Modal.Title>Address</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Address getAddressData={props.getAddressData}
                             handleClose={handleClose}   />
                </Modal.Body>               
            </Modal>
        </div>
    )
}
