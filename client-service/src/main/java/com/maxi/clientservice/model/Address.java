package com.maxi.clientservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Address {

    private Long id;
    private String address;
    private String country;
    private String city;
    private String province;

}
