package com.maxi.address.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxi.address.entity.Address;
import com.maxi.address.service.AddressService;
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
@RequestMapping("api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@Valid @RequestBody Address address, BindingResult result){
        log.info("Create address {}", address);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Address addressAux = addressService.addAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressAux);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long id,@RequestBody Address address){
        log.info("Update address id {}", id);
        Address addressAux = addressService.updateAddress(address);
        if (address == null){
            log.error("Address {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressAux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable("id") Long id){
        log.info("Get Address id {}", id);
        Address address = addressService.getAddress(id);
        if (address == null){
            log.error("Address id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable("id") Long id){
        log.info("Delete address id {}", id);
        Address address = addressService.deleteAddress(id);
        if (address == null){
            log.error("Address id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<List<Address>> findByClientId(@PathVariable("id") Long clientId){
        log.info("Find by client with client id {}", clientId);
        List<Address> addresses = addressService.findByClientId(clientId);
        if (addresses.isEmpty()){
            log.error("Address client id {} no content", clientId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<List<Address>> findBySupplierId(@PathVariable("id") Long supplierId){
        log.info("Find by supplier with id {}", supplierId);
        List<Address> addresses = addressService.findBySupplierId(supplierId);
        if (addresses.isEmpty()){
            log.error("Address supplier id {} no content", supplierId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addresses);
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
