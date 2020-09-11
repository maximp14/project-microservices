package com.maxi.clientservice.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class Supplier {

    private Long id;
    private String name;
    private Long cuit;
    private String category;
    private Date registrationDate;
    private Date withdrawDate;
    private String description;
    List<Address> addressList;

}
