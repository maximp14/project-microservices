package com.maxi.address.service;

import com.maxi.address.entity.Address;

import java.util.List;

public interface AddressService {

    Address addAddress(Address address);
    Address deleteAddress(Long id);
    Address updateAddress(Address address);
    Address getAddress(Long id);
    List<Address> findByClientId(Long clientId);
    List<Address> findBySupplierId(Long supplierId);
}
