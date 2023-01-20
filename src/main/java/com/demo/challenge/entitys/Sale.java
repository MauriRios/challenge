/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    private Date date;
    
    private int quantity;
    
    @Column(name = "totalPrice")
    private double total;

 
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "sale_product",
        joinColumns = @JoinColumn(name = "sale_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
   
    public Sale() {
    }

    public Sale(int id, Date date, int quantity, double total, List<Product> products, Customer customer) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.total = total;
        this.products = products;
        this.customer = customer;
    }

    
}
