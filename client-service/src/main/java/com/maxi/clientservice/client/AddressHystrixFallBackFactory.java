package com.maxi.clientservice.client;

import com.maxi.clientservice.model.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressHystrixFallBackFactory implements AddressClient{

    @Override
    public ResponseEntity<List<Address>> findByClientId(Long clientId) {
        List<Address> addresses = new ArrayList<>();
        Address address = Address.builder()
                .address("none")
                .city("none")
                .province("none").build();

        addresses.add(address);
        return ResponseEntity.ok(addresses);
    }
}
