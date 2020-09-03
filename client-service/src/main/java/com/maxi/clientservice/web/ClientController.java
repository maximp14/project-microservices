package com.maxi.clientservice.web;

import com.maxi.clientservice.entity.Client;
import com.maxi.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("Getting all clients");
        List<Client> clients = clientService.listAllClients();
        if (clients.isEmpty()){
            log.error("No content client list");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id){
        log.info("Getting client id {}",id);
        Client client = clientService.getClient(id);
        if (client == null){
            log.error("Client id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        log.info("Creating client {}", client);
        Client clientCreated = clientService.createClient(client);
        // VALIDATION ---------
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
        log.info("Updating id {} client {}", id, client);
        client.setId(id);
        Client clientUpdated = clientService.updateClient(client);
        /// VALIDATION
        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id){
        log.info("Deleting client id {}", id);
        Client client = clientService.getClient(id);
        if (client == null){
            log.error("Client {} not found", id);
            return ResponseEntity.notFound().build();
        }
        client = clientService.deleteClient(client);
        return ResponseEntity.ok(client);
    }

}
