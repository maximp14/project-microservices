package com.maxi.supplierservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxi.supplierservice.entity.Supplier;
import com.maxi.supplierservice.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier, BindingResult result){
        log.info("Create supplier {}", supplier);
        if (result.hasErrors()){
            log.error("Error creating supplier {}",supplier);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Supplier supplierCreated = supplierService.createSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable("id") Long id){
        log.info("Getting supplier id {}", id);
        Supplier supplier = supplierService.getSupplier(id);
        if (supplier == null){
            log.error("Supplier id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") Long id ,@RequestBody Supplier supplier){
        log.info("Updating supplier id {}", id);
        supplier.setId(id);
        Supplier supplierUpdated = supplierService.updateSupplier(supplier);
        if (supplierUpdated == null){
            log.error("Supplier id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplierUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") Long id){
        log.info("Deleting supplier id {}", id);
        Supplier supplierDeleted = supplierService.getSupplier(id);
        if (supplierDeleted == null){
            log.error("Supplier id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        supplierDeleted = supplierService.deleteSupplier(id);
        return ResponseEntity.ok(supplierDeleted);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Supplier>> getSupplierByClientId(@PathVariable("id") Long clientId){
        log.info("Getting suppliers to the client id {}",clientId);
        List<Supplier> suppliers = supplierService.findSupplierByClientId(clientId);
        if (suppliers == null){
            log.error("No supplier for the client id {}",clientId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suppliers);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
