package com.maxi.clientservice.web;

import com.maxi.clientservice.entity.Address;
import com.maxi.clientservice.entity.Client;
import com.maxi.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients(){
        List<Client> clients = clientService.listAllClients();
        if (clients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id){
        Client client = clientService.getClient(id);
        if (client == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client clientCreated = clientService.createClient(client);
        // VALIDATION ---------
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
        client.setId(id);
        Client clientUpdated = clientService.updateClient(client);
        /// VALIDATION
        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id){
        Client client = clientService.getClient(id);
        if (client == null){
            return ResponseEntity.notFound().build();
        }
        client = clientService.deleteClient(client);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Client> addNewAddressToClient(@PathVariable("id") Long id, @RequestBody Address address){
        Client client = clientService.getClient(id);
        if (client == null){
            return ResponseEntity.notFound().build();
        }
        client = clientService.addAddress(id, address);
        return ResponseEntity.ok(client);
    }
}
