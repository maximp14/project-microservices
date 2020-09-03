package com.maxi.clientservice.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Address {

    private Long id;
    private String address;
    private String city;
    private String province;
    private String status;
    private Long clientId;

}
