/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;



import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name= "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(length = 45, nullable = false)
    private String name;
    
    @NotNull
    @Column(length = 30, nullable = false)
    private String description;
    
    @NotNull
    @Column(nullable = false)
    private int price;
    
    @NotNull
    @Column(nullable = false)
    private int stock;
    
    @NotNull
    @Column(nullable = false)
    private boolean status;
    
    @Column(nullable = true)
    private int quantity;
    
   
    @JsonIgnore
    @ManyToMany(mappedBy = "prodList")
    private List<Provider> providerList = new ArrayList<>();
    

    public Product() {
    }

    public Product(int id, String name, String description, int price, int stock, boolean status, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.quantity = quantity;
    }
  
    
    public void addProvider(Provider provider) {
        this.providerList.add(provider);
    }
    

}
