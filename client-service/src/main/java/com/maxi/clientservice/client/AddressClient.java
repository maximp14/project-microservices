package com.maxi.clientservice.client;


import com.maxi.clientservice.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "address-service")
@RequestMapping("api/address")
public interface AddressClient {

    @GetMapping("/clients/{id}")
    public ResponseEntity<List<Address>> findByClientId(@PathVariable("id") Long clientId);
}
