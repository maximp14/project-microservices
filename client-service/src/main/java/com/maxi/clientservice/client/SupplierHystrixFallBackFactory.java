package com.maxi.clientservice.client;

import com.maxi.clientservice.model.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierHystrixFallBackFactory implements SupplierClient{

    @Override
    public ResponseEntity<List<Supplier>> getSupplierByClientId(Long clientId) {
        List<Supplier> suppliers = new ArrayList<>();
        Supplier supplier = Supplier.builder()
                .name("none")
                .description("none")
                .category("none")
                .addressList(null).build();
        suppliers.add(supplier);
        return ResponseEntity.ok(suppliers);
    }
}
