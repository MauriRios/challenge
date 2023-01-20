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
    
    @Column(length = 45, nullable = false, unique = true)
    private String name;
    
    @Column(length = 11, nullable = false, unique = true)
    private int cuit;
    
    @Column(length = 11, nullable = false, unique = true)
    private int phone;
    
    @Column(length = 11, nullable = false, unique = true)
    private String address;
    
    @ManyToMany
    @JoinTable(name = "provider_product",
        joinColumns = @JoinColumn(name = "provider_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Provider() {
    }

    public Provider(int id, String name, int cuit, int phone, String address, List<Product> products) {
        this.id = id;
        this.name = name;
        this.cuit = cuit;
        this.phone = phone;
        this.address = address;
        this.products = products;
    }
    
    public void addProduct(Product product) {
        this.products.add(product);
        product.addProvider(this);
    }
    
    
    
    
}
