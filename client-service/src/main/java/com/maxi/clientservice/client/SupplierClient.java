package com.maxi.clientservice.client;

import com.maxi.clientservice.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "supplier-service", fallbackFactory = SupplierHystrixFallBackFactory.class)
public interface SupplierClient {

    @GetMapping("api/supplier/client/{id}")
    public ResponseEntity<List<Supplier>> getSupplierByClientId(@PathVariable("id") Long clientId);

}
