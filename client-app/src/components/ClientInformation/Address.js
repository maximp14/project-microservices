import React, { useState } from 'react'
import { Form, Col, Button } from "react-bootstrap"

export default function Address(props) {

    const [addressForm, setAddressForm] = useState(initialAddressFormValues())

    const onChange = e =>{
        setAddressForm({...addressForm, [e.target.name]: e.target.value})       
    }

    const onSubmit  = () =>{    
        props.handleClose()
        props.getAddressData(addressForm)
    }
    
    return (
        <div>
            <Form onChange={onChange} >
                <Form.Row>
                    <Form.Group as={Col} >
                    <Form.Label>Address</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="Enter address"
                    name="address"
                    defaultValue={addressForm.address}                     
                    />
                    </Form.Group>
                </Form.Row>
                
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridState">
                        <Form.Label>Type</Form.Label>
                        <Form.Control as="select"
                        defaultValue="Choose..."
                        name="type"
                        defaultValue={addressForm.type}  
                        > 
                            <option>Choose...</option>                           
                            <option value="legal">Legal</option>
                            <option value="personal">Personal</option>
                            <option value="comercial">Comercial</option>
                        </Form.Control>
                    </Form.Group>
                </Form.Row>

                <Form.Row>
                    <Form.Group as={Col} >
                    <Form.Label>Country</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="Country"
                    name="country"
                    defaultValue={addressForm.country}                      
                    />
                    </Form.Group>

                    <Form.Group as={Col} >
                    <Form.Label>State</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="State"  
                    name="province"
                    defaultValue={addressForm.province}                   
                    />
                    </Form.Group>

                    <Form.Group as={Col} >
                    <Form.Label>City</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="City" 
                    name="city"
                    defaultValue={addressForm.city}                    
                    />
                    </Form.Group>
                </Form.Row>                
                <Button variant="primary" onClick={onSubmit}>
                    Save Address
                </Button>
            </Form>
        </div>
    )
}

function initialAddressFormValues(){
    return{
        id: "",
        address: "",
        type: "",
        country: "",
        province: "",
        city: "",
        clientName: ""
    }
}