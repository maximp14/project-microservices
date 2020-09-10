package com.maxi.supplierservice.entity;

import com.maxi.supplierservice.model.Address;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long id;

    @NotNull(message = "Name required")
    private String name;

    @NotNull(message = "Cuit required")
    private Long cuit;

    private String category;
    private Date registrationDate;
    private Date withdrawDate;
    private String description;

    private String status;

    private Long clientId;

    @Transient
    List<Address> addressList;

    @PrePersist
    public void prePersist(){
        this.registrationDate = new Date();
        this.status = "ACTIVE";
    }
}
