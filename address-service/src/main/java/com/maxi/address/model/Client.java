package com.maxi.address.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Client {
    private Long id;
    private String name;
    private Long cuit;
    private String description;
}
