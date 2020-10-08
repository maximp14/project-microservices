import React from 'react'
import { Nav , Navbar, NavDropdown } from "react-bootstrap"


export default function WebNavBar() {
    return (
        <div className="nav-bar">
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Brand href="/">ERP TEST</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">
                <NavDropdown title="Dropdown" id="collasible-nav-dropdown">
                    {/* <NavDropdown.Item href="/addClient">Add Client</NavDropdown.Item> */}
                    <NavDropdown.Item href="/test">Add Client TEST</NavDropdown.Item>
                    <NavDropdown.Item href="/clients">Show Clients</NavDropdown.Item>
                    
                </NavDropdown>
                </Nav>                
                </Navbar.Collapse>                
            </Navbar>
        </div>
    )
}
