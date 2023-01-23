/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.repository;

import com.demo.challenge.dto.ProductDTO;
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
    
    @Query(value = "SELECT products.name, products.stock, providers.providerName, providers.phone FROM products JOIN provider_product ON provider_product.product_id = products.id JOIN providers ON providers.id = provider_product.provider_id WHERE products.stock <= :stock GROUP BY products.id", nativeQuery = true)
    List<ProductDTO> findLowStockProducts(@Param("stock") int stock);
    
   
}
