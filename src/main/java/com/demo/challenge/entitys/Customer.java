/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

/**
 *
 * @author mauri
 */


@Getter @Setter
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(length = 20, nullable = false)
    private String name;
    
    @NotNull
    @Column(length = 20, nullable = false)
    private String lastName;
    
    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private Integer dni;
    
    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private Integer phone;
    
    @NotNull
    @Column(length = 30, nullable = false)
    private String address;
    
    @NotNull
    @Column(nullable = false)
    private Boolean status;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sale> purchases;


}
