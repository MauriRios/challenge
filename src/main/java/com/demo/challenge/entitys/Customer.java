/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private int id;
    
    @NotNull
    @Column(length = 20, nullable = false)
    private String name;
    
    @NotNull
    @Column(length = 20, nullable = false)
    private String lastName;
    
    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private int dni;
    
    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private int phone;
    
    @NotNull
    @Column(length = 30, nullable = false)
    private String address;
    
    @NotNull
    @Column(nullable = false)
    private boolean status;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

    public Customer() {
    }

    public Customer(int id) {
        this.id = id;
    }

    

    
    
   
    
}
