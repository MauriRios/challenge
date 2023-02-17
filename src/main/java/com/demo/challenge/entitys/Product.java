/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.entitys;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

/**
 *
 * @author mauri
 */


@Getter @Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(length = 45, nullable = false)
    private String name;
    
    @NotNull
    @Column(length = 30, nullable = false)
    private String description;
    
    @NotNull
    @Column(nullable = false)
    private Integer price;
    
    @NotNull
    @Column(nullable = false)
    private Integer stock;
    
    @NotNull
    @Column(nullable = false)
    private Boolean status = true;
    
    @Column(nullable = true)
    private Integer quantity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "fk_Product_Provider"))
    private Provider provider;

    @ManyToMany(mappedBy = "products")
    private List<Sale> sales;


}
