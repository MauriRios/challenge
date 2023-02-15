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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "providers")
public class Provider {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(length = 45, nullable = false)
    private String providerName;
    @NotNull
    @Column(length = 25, nullable = false)
    private String providerLastName;
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
    private Boolean status;
    
    @ManyToMany
    @JoinTable(name = "provider_product",
        joinColumns = @JoinColumn(name = "provider_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> prodList;

    @OneToMany(mappedBy = "provider")
    private List<Sale> saleList;

//    public void addProduct(Product product) {
//        this.prodList.add(product);
//        product.addProvider(this);
//    }
    
    
    
    
}
