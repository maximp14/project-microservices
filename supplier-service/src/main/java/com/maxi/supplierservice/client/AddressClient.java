package com.maxi.supplierservice.client;

import com.maxi.supplierservice.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "address-service", fallback = AddressHystrixFallBackFactory.class)
public interface AddressClient {

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<List<Address>> findBySupplierId(@PathVariable("id") Long supplierId);
}
