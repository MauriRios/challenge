/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.repositories;

import com.demo.challenge.entities.Product;
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

    @Query(value = "SELECT * FROM PRODUCTS where stock <= :stock", nativeQuery = true )
    List<Product> findProductsByLowStock(@Param("stock") Integer stock);
}
