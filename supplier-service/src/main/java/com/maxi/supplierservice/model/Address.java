package com.maxi.supplierservice.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Address {

    private Long id;
    private String address;
    private String country;
    private String city;
    private String province;
    private Long supplierId;

}
