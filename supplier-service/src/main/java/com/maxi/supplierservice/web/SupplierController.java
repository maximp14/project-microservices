package com.maxi.supplierservice.web;

import com.maxi.supplierservice.entity.Supplier;
import com.maxi.supplierservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier){
        /// validation & log
        Supplier supplierCreated = supplierService.createSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable("id") Long id){
        Supplier supplier = supplierService.getSupplier(id);
        if (supplier == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") Long id ,@RequestBody Supplier supplier){
        supplier.setId(id);
        Supplier supplierUpdated = supplierService.updateSupplier(supplier);
        if (supplierUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplierUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") Long id){
        Supplier supplierDeleted = supplierService.getSupplier(id);
        if (supplierDeleted == null){
            return ResponseEntity.notFound().build();
        }
        supplierDeleted = supplierService.deleteSupplier(id);
        return ResponseEntity.ok(supplierDeleted);
    }
}
