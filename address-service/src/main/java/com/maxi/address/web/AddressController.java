package com.maxi.address.web;

import com.maxi.address.entity.Address;
import com.maxi.address.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// TODOs WORK WITH VALIDATION DEPENDENCY /// USE UUID FOR THE IDs ?

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
    public ResponseEntity<Address> addAddress(@RequestBody Address address){
        log.info("Create address {}", address);
        Address addressAux = addressService.addAddress(address);
        /// IMPROVE VALIDATION--

        return ResponseEntity.status(HttpStatus.CREATED).body(addressAux);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long id,@RequestBody Address address){
        log.info("Update address id {}", id);
        Address addressAux = addressService.updateAddress(address);
        /// IMPROVE VALIDATION--
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
}
