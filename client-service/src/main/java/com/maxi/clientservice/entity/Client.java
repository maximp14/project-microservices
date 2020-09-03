package com.maxi.clientservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maxi.clientservice.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

//    @NotNull(message = "Address is required")
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JoinColumn(name = "add_id")
//    private List<Address> address;

    @Transient
    private List<Address> addresses;

    private String status;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
        this.status = "ACTIVE";
    }
}
