package com.maxi.supplierservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @PrePersist
    public void prePersist(){
        this.registrationDate = new Date();
        this.status = "ACTIVE";
    }
}
