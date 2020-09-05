package com.maxi.supplierservice.service;

import com.maxi.supplierservice.entity.Supplier;

public interface SupplierService {

    Supplier createSupplier(Supplier supplier);
    Supplier getSupplier(Long id);
    Supplier updateSupplier (Supplier supplier);
    Supplier deleteSupplier (Long id);
}
