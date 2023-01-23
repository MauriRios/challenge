/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.*;

/**
 *
 * @author mauri
 */


@Getter @Setter
@Entity
@Table(name = "providers")
public class Provider {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(length = 45, nullable = false)
    private String providerName;
    
    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private int cuit;
    
    @NotNull
    @Column(length = 11, nullable = false, unique = true)
    private int phone;
    
    @NotNull
    @Column(length = 11, nullable = false)
    private String address;
    
    @NotNull
    private boolean status;
    
    @ManyToMany
    @JoinTable(name = "provider_product",
        joinColumns = @JoinColumn(name = "provider_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> prodList;

    public Provider() {
    }

    public Provider(int id, String providerName, int cuit, int phone, String address, boolean status, List<Product> prodList) {
        this.id = id;
        this.providerName = providerName;
        this.cuit = cuit;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.prodList = prodList;
    }

   
    
    public void addProduct(Product product) {
        this.prodList.add(product);
        product.addProvider(this);
    }
    
    
    
    
}
