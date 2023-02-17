/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;

import com.demo.challenge.dto.ProductSaleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
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
@AllArgsConstructor
@NoArgsConstructor
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
    private BigDecimal total;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sale_product",
        joinColumns = @JoinColumn(name = "sale_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id")
    private Provider provider_id;


    
}
