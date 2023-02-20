/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dtos.LowProductProviderDTO;
import com.demo.challenge.dtos.ProductDTO;
import com.demo.challenge.entities.Product;

import java.util.List;

/**
 * @author mauri
 */

public interface IProductService {

    List<ProductDTO> getProducts();

    List<ProductDTO> getProductsByStatus(Boolean status);

    ProductDTO findProductById(int id);

    String deleteProduct(int id);

    String updateProduct(Product product);

    String saveProduct(Product product);

    String activateProduct(int id);

    String deactivateProduct(int id);

    // querys

    List<LowProductProviderDTO> findProductsByLowStock(Integer id);


}
