package com.maxi.supplierservice.service;

import com.maxi.supplierservice.entity.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier createSupplier(Supplier supplier);
    Supplier getSupplier(Long id);
    Supplier updateSupplier (Supplier supplier);
    Supplier deleteSupplier (Long id);
    List<Supplier> findSupplierByClientId(Long clientId);
}
