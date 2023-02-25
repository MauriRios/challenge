/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author mauri
 */


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(length = 45, nullable = false)
    private String providerName;
    @NotNull
    @Column(length = 25, nullable = false)
    private String providerLastName;
    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private Integer cuit;

    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private Integer phone;

    @NotNull
    @Column(length = 11, nullable = false)
    private String address;

    @NotNull
    private Boolean status;

    @JsonManagedReference
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> prodList = new ArrayList<>();

    @OneToMany(mappedBy = "provider_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sale> saleList;


}
