import React from 'react';
import AddClients from './components/AddClients/AddClients';
import WebNavBar from './components/NavBar/WebNavBar';
import ShowClients from './components/ShowClients/ShowClients';
import AddClientsTest from "./components/AddClients/AddClientsTest"
import { BrowserRouter, Switch, Route } from "react-router-dom";



function App() {
  return (
    <BrowserRouter>
      <WebNavBar />
      <Switch>
        <Route path="/clients" exact component={ShowClients}  />
        <Route path="/addClient" exact component={AddClients} />
        <Route path="/test" exact component={AddClientsTest} />         
      </Switch>
    </BrowserRouter>
  );
}

export default App;
