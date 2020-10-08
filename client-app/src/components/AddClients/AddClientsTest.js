import React, { useState } from 'react'
import { Form, Button } from "react-bootstrap"
import { Flip, ToastContainer, toast } from "react-toastify"
import { values, size } from "lodash"
import { createClient, createAddress } from "../../api/Request"
import { v4 as uuidv4 } from "uuid"

import "./AddClients.css"
import ModalAddress from '../ClientInformation/ModalAddress'
import ListAddress from '../ClientInformation/ListAddress'


export default function AddClientsTest() {

    /// State para apertura y cierre de modal
    const [showModal, setShowModal] = useState(false)

    /// State para cargar los datos de cliente
    const [clientData, setClientData] = useState(initialFormValues())

    const [addressData, setAddressData] = useState([])


    /// Paso por props al form de data
    const getAddressData = data =>{
       // data.id = uuidv4()
        setAddressData(data)
    }

    /// onChange para captar los datos del form
    const onChange = e =>{
        setClientData({...clientData, [e.target.name]: e.target.value})
    }

    /// onSubmit para mandar al backend client & address
    const onSubmit = (e) =>{
        e.preventDefault()       

        let validCount = 0
        let validCountAddress = 0

        // validacion para saber si client esta vacio
        values(clientData).some(value =>{
            value && validCount++
            return null
        })

        // validacion para saber si address esta vacio 
        // *esto deberia hacerlo en address
        values(addressData).some(value =>{
            value && validCountAddress++
            return null
        })
      
        /// Si client data no esta vacio hago el post
        if(size(clientData) !== validCount){
            toast.warning("Complete all fields")
        }else{
            createClient(clientData).then(response =>{
                if(response.code){
                    toast.warning(response.message)
                }else{
                    toast.success("Client created")
                    setClientData(initialFormValues())
                }
            }).catch(() =>{
                toast.error("Something went wrong")
            })            
           
            // Una vez hecho el post de client, confirmo si no esta vacio
            // address, hago la relacion para el back y despues el post
            if(size(addressData) > 0){
                addressData.clientName = clientData.name
                delete addressData.name

                console.log("POST Address", addressData)

                createAddress(addressData).then(response =>{
                    if(response.code){
                        toast.warning(response.message)
                    }
                }).catch(() =>{
                    toast.error("Something went wrong")
                })
            }
        }

    }
    
    /// Cambia de estado para mostrar el modal
    const modalOnclick = () =>{
        setShowModal(true)             
    }

    /// Envio por props al modal para que pueda devolver el estado
    const changeModalStatus = (state) =>{
        setShowModal(state)
    }

    return (
        <div className="client-form">
            <Form onSubmit={onSubmit}>                
                <Form.Group>
                    <Form.Label>Name</Form.Label>
                        <Form.Control 
                        type="text" 
                        placeholder="Client Name"
                        onChange={onChange}
                        name="name"
                        defaultValue={clientData.name}                                      
                        />
                        <br />
                    <Form.Label>CUIT</Form.Label>
                        <Form.Control 
                        type="text" 
                        placeholder="Client CUIT"
                        onChange={onChange}
                        name="cuit"
                        defaultValue={clientData.cuit}                                         
                        />
                    <br />                      
                </Form.Group>
               
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Description</Form.Label>
                    <Form.Control 
                    as="textarea" 
                    rows="3" 
                    placeholder="Enter description"
                    onChange={onChange}
                    name="description"
                    defaultValue={clientData.description}                                     
                    />
                </Form.Group>

                <Form.Group controlId="exampleForm.ControlTextarea1">
                <Button variant="secondary" onClick={modalOnclick}>Add Address</Button>                              
                {
                   showModal === true &&                    
                   <ModalAddress 
                   changeModalStatus={changeModalStatus} 
                   getAddressData={getAddressData}
                   />
                }
                </Form.Group>

                 <Form.Group controlId="exampleForm.ControlTextarea1">
                    <ListAddress addressData={addressData} />
                </Form.Group>

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


