package com.maxi.supplierservice.service.impl;

import com.maxi.supplierservice.client.AddressClient;
import com.maxi.supplierservice.entity.Supplier;
import com.maxi.supplierservice.model.Address;
import com.maxi.supplierservice.repository.SupplierRepository;
import com.maxi.supplierservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final AddressClient addressClient;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, AddressClient addressClient) {
        this.supplierRepository = supplierRepository;
        this.addressClient = addressClient;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null){
            List<Address> addresses = addressClient.findBySupplierId(supplier.getId()).getBody();
            supplier.setAddressList(addresses);
        }
        return supplier;
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        Supplier supplierAux = getSupplier(supplier.getId());
        if (supplierAux == null){
            return null;
        }
        supplierAux.setName(supplier.getName());
        supplierAux.setCategory(supplier.getCategory());
        supplierAux.setDescription(supplier.getDescription());
        supplierRepository.save(supplierAux);
        return supplierAux;
    }

    @Override
    public Supplier deleteSupplier(Long id) {
        Supplier supplierAux = getSupplier(id);
        if (supplierAux == null){
            return null;
        }
        supplierAux.setStatus("DELETED");
        supplierAux.setWithdrawDate(new Date());
        supplierRepository.save(supplierAux);
        return supplierAux;
    }
}
