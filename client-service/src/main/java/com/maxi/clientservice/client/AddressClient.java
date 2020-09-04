package com.maxi.clientservice.client;


import com.maxi.clientservice.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "address-service", fallback = AddressHystrixFallBackFactory.class)
public interface AddressClient {

    @GetMapping("/clients/{id}")
    ResponseEntity<List<Address>> findByClientId(@PathVariable("id") Long clientId);
}
