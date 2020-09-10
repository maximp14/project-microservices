package com.maxi.clientservice.service;

import com.maxi.clientservice.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> listAllClients();
    Client getClient(Long id);
    Client createClient(Client client);
    Client updateClient(Client client);
    Client deleteClient(Client client);

}
