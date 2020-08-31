package com.maxi.clientservice.service.impl;

import com.maxi.clientservice.entity.Address;
import com.maxi.clientservice.entity.Client;
import com.maxi.clientservice.repository.AddressRepository;
import com.maxi.clientservice.repository.ClientRepository;
import com.maxi.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
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
        if (id == null){
            return null;
        }
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client createClient(Client client) {
        Client clientAux = this.getClient(client.getId());
        if (clientAux != null){
            throw new IllegalStateException("User already created");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        Client clientAux = this.getClient(client.getId());
        if (clientAux == null){
            throw new IllegalStateException("User cannot be updated, because it does not exist");
        }
        clientAux.setId(client.getId());
        clientAux.setDescription(client.getDescription());
        clientAux.setName(client.getName());
        clientRepository.save(clientAux);
        return clientAux;
    }

    @Override
    public Client deleteClient(Client client) {
        Client clientAux = this.getClient(client.getId());
        if (clientAux == null){
            throw new IllegalStateException("User cannot be deleted, because it does not exist");
        }
        clientAux.setStatus("DELETED");
        clientRepository.save(clientAux);
        return clientAux;
    }

    @Override
    public Client addAddress(Long id, Address address) {
        Client client = getClient(id);
        if (client == null){
            return null;
        }
        addressRepository.save(address);
        List<Address> addresses = client.getAddress();
        addresses.add(address);
        client.setAddress(addresses);
        return clientRepository.save(client);
    }
}
