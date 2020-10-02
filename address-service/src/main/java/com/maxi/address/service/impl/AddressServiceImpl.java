package com.maxi.address.service.impl;

import com.maxi.address.client.ClientClient;
import com.maxi.address.entity.Address;
import com.maxi.address.model.Client;
import com.maxi.address.repository.AddressRepository;
import com.maxi.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ClientClient clientClient;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ClientClient clientClient) {
        this.addressRepository = addressRepository;
        this.clientClient = clientClient;
    }

    @Override
    public Address addAddress(Address address) {
        Address addressAux = this.getAddress(address.getId());
        if (addressAux != null){
            return null;
        }
        if (!addressAux.getClientName().isEmpty()){
            Client client = clientClient.findClientByName(addressAux.getClientName()).getBody();
            addressAux.setClientId(client.getId());
        }
        return addressRepository.save(address);
    }

    @Override
    public Address deleteAddress(Long id) {
        Address address = this.getAddress(id);
        if (address == null){
            return null;
        }
        address.setStatus("DELETED");
        address = addressRepository.save(address);
        return address;
    }

    @Override
    public Address updateAddress(Address address) {
        Address addressAux = this.getAddress(address.getId());
        if (addressAux == null){
            return null;
        }
        addressAux.setAddress(address.getAddress());
        addressAux.setCity(address.getCity());
        addressAux.setProvince(address.getProvince());
        addressRepository.save(addressAux);
        return addressAux;
    }

    @Override
    public Address getAddress(Long id) {
        if (id == null){
            return null;
        }
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findByClientId(Long clientId) {
        return addressRepository.findByClientId(clientId);
    }

    @Override
    public List<Address> findBySupplierId(Long supplierId) {
        return addressRepository.findBySupplierId(supplierId);
    }
}
