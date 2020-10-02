package com.maxi.address.client;

import com.maxi.address.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "client-service", fallbackFactory = ClientHystrixFallBackFactory.class)
public interface ClientClient {

    @GetMapping("api/address/name/{name}")
    public ResponseEntity<Client> findClientByName(@PathVariable("name") String name);
}
