import React, { useState } from 'react'
import { Form, Button, Col } from "react-bootstrap"
import { values, size } from "lodash"
import { Flip, toast, ToastContainer } from "react-toastify"
import { createClient, createAddress } from "../../api/Request"

import "./AddClients.css"

export default function AddClients() {

    const [formData, setFormData] = useState(initialFormValues())

    const [addressData, setAddressData] = useState(initialAddressValues)


    const onSubmit = e =>{
        e.preventDefault()
        console.log(formData)
        

        let validCount = 0

        values(formData).some(value =>{
            value && validCount++
            return null
        })

        if(size(formData) !== validCount && size(addressData) !== validCount){
            toast.warning("Complete all the fields")
        }else{
           createClient(formData).then(response =>{
               if(response.code){
                   toast.warning(response.message)
               }else{
                   toast.success("Client Added")
                   setFormData(initialFormValues())
               }
           }).catch (()=>{
               toast.error("Something went wrong")
           })
          
           addressData.clientName = formData.name
           delete addressData.name
           console.log("seteado", addressData)
           createAddress(addressData).then(response =>{
            if(response.code){
                toast.warning(response.message)
            }else{                
                setAddressData(initialAddressValues())
                console.log("reinici address")
            }
            }).catch (()=>{
                toast.error("Something went wrong with address")               
            })
        }
        setFormData(initialFormValues())
        setAddressData(initialAddressValues())
    }

    const onChange = e =>{
        setFormData({...formData, [e.target.name]: e.target.value})
        setAddressData({...addressData, [e.target.name]: e.target.value})
    }

    return (
        <div className="client-form">
            <Form onSubmit={onSubmit} onChange={onChange}>
                
                <Form.Group>
                    <Form.Label>Name</Form.Label>
                        <Form.Control 
                        type="text" 
                        placeholder="Client Name" 
                        name="name"
                        defaultValue = {formData.name}                    
                        />
                        <br />
                    <Form.Label>CUIT</Form.Label>
                        <Form.Control 
                        type="text" 
                        placeholder="Client CUIT" 
                        name="cuit"
                        defaultValue = {formData.cuit}                   
                        />
                    <br />                      
                </Form.Group>
               
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Description</Form.Label>
                    <Form.Control 
                    as="textarea" 
                    rows="3" 
                    placeholder="Enter description" 
                    name="description"
                    defaultValue = {formData.description}                    
                    />
                </Form.Group>

                

                <Form.Row>
                    <Form.Group as={Col} >
                    <Form.Label>Address</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="Enter address" 
                    name="address"
                    defaultValue={addressData.address}
                    />
                    </Form.Group>

                </Form.Row>

                <Form.Row>
                    <Form.Group as={Col} >
                    <Form.Label>Country</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="Country" 
                    name="country"
                    defaultValue={addressData.country}
                    />
                    </Form.Group>

                    <Form.Group as={Col} >
                    <Form.Label>State</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="State" 
                    name="province"
                    defaultValue={addressData.province}
                    />
                    </Form.Group>

                    <Form.Group as={Col} >
                    <Form.Label>City</Form.Label>
                    <Form.Control 
                    type="text" 
                    placeholder="City" 
                    name="city"
                    defaultValue={addressData.city}
                    />
                    </Form.Group>
                </Form.Row>

                <Button variant="primary" type="submit">Confirm</Button>
            </Form>
             
            <ToastContainer 
                transition={Flip}
            />
        </div>
    )
}

function initialFormValues(){
    return{
        name: "",
        cuit: "",
        description: ""
    }
}

function initialAddressValues(){
    return{
        address: "",
        country: "",
        province: "",
        city: "",
        clientName: ""
    }
}
