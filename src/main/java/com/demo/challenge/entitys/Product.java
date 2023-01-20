/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;



import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
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
    private String name;
    
    @NotNull
    private String description;
    
    @NotNull
    private int price;
    
    @NotNull
    private int stock;
   
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Provider> providerList = new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Sale> sales;

    public Product() {
    }

    public Product(int id, String name, String description, int price, int stock, List<Provider> providerList, List<Sale> sales) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.providerList = providerList;
        this.sales = sales;
    }   
    
    public void addProvider(Provider provider) {
        this.providerList.add(provider);
    }
    

}
