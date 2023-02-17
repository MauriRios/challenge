/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.repository;

import com.demo.challenge.dto.ProductDTO;
import com.demo.challenge.dto.ProductProviderDTO;
import com.demo.challenge.entitys.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauri
 */

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

//  @Query(value = "SELECT products PRODUCTS where stock <= ?1")
//  List<ProductProviderDTO> findLowStockProducts(Integer stock);


}
