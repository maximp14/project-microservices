package com.maxi.supplierservice.client;

import com.maxi.supplierservice.model.Address;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressHystrixFallBackFactory implements FallbackFactory<AddressClient> {

    @Override
    public AddressClient create(Throwable throwable) {
        return new AddressClient() {
            @Override
            public ResponseEntity<List<Address>> findBySupplierId(Long supplierId) {
                List<Address> addresses = new ArrayList<>();
                Address address = Address.builder()
                        .address("none")
                        .city("none")
                        .province("none").build();

                addresses.add(address);
                return ResponseEntity.ok(addresses);
            }
        };
    }
}
