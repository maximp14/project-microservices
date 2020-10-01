package com.maxi.clientservice.service.impl;


import com.maxi.clientservice.client.AddressClient;
import com.maxi.clientservice.client.SupplierClient;
import com.maxi.clientservice.entity.Client;
import com.maxi.clientservice.model.Address;
import com.maxi.clientservice.model.Supplier;
import com.maxi.clientservice.repository.ClientRepository;
import com.maxi.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AddressClient addressClient;
    private final SupplierClient supplierClient;



    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, AddressClient addressClient, SupplierClient supplierClient) {
        this.clientRepository = clientRepository;
        this.addressClient = addressClient;
        this.supplierClient = supplierClient;
    }

    @Override
    public List<Client> listAllClients() {
        List<Client> clients = clientRepository.findAll().stream()
                .filter(client -> client.getStatus().equals("ACTIVE"))
                .collect(Collectors.toList());
         return clients;
    }

    @Override
    public Client getClient(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null){
            List<Address> addresses = addressClient.findByClientId(client.getId()).getBody();
            List<Supplier> suppliers = supplierClient.getSupplierByClientId(client.getId()).getBody();
            client.setAddresses(addresses);
            client.setSuppliers(suppliers);
        }
        return client;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        Client clientAux = this.getClient(client.getId());
        if (clientAux == null){
            return null;
        }
        //clientAux.setId(client.getId());
        clientAux.setDescription(client.getDescription());
        clientAux.setName(client.getName());
        return clientRepository.save(clientAux);
    }

    @Override
    public Client deleteClient(Client client) {
        Client clientAux = this.getClient(client.getId());
        if (clientAux == null){
            return null;
        }
        clientAux.setStatus("DELETED");
        clientRepository.save(clientAux);
        return clientAux;
    }


    @Override
    public Client findByName(String name) {
        Client client = clientRepository.findByName(name);
        if (client == null){
            return null;
        }
        return client;
    }
}
