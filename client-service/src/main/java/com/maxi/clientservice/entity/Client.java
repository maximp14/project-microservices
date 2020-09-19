package com.maxi.clientservice.entity;

import com.maxi.clientservice.model.Address;
import com.maxi.clientservice.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @NotNull(message = "Name required")
    private String name;
    private Long cuit;
    private String description;

    @Column(name = "created_at")
    private Date createdAt;


    @Transient
    private List<Address> addresses;

    @Transient
    private List<Supplier> suppliers;

    private String status;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
        this.status = "ACTIVE";
    }

}
