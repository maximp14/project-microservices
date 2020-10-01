package com.maxi.address.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Country is required")
    private String country;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "Province is required")
    private String province;

    private String status;

    @PrePersist
    public void PrePersist(){
        this.status = "CREATED";
    }
}
