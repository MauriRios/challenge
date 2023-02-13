/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Table (name = "sales")
public class Sale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(nullable = false)
    private LocalDate date;
    @NotNull
    @Column(nullable = false)
    private int quantity;
    @NotNull
    @Column(nullable = false)
    private double total;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "sale_product",
        joinColumns = @JoinColumn(name = "sale_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
   
    public Sale() {
    }

    public Sale(int id, LocalDate date, int quantity, double total, List<Product> products, Customer customer) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.total = total;
        this.products = new ArrayList<>();
        this.customer = customer;
    }

    //probando
   // public void addProduct(Product product, int quantity) {
   //       products.add(product);
    //  }

    
}
