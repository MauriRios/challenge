/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dtos.LowProductProviderDTO;
import com.demo.challenge.dtos.ProductDTO;
import com.demo.challenge.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author mauri
 */

public interface IProductService {

    List<ProductDTO> getProducts();

    List<ProductDTO> getActiveProducts(Boolean status);

    ProductDTO findProductById(int id);

    ResponseEntity<String> deleteProduct(int id);

    ResponseEntity<String> updateProduct(Product product);

    ResponseEntity<String> saveProduct(Product product);

    ResponseEntity<String> activateProduct(int id);

    ResponseEntity<String> deactivateProduct(int id);

    // querys

    List<LowProductProviderDTO> findProductsByLowStock(Integer id);


}
