/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dto.ProductDTO;
import com.demo.challenge.dto.ProductListDTO;
import com.demo.challenge.dto.ProductProviderDTO;
import com.demo.challenge.entitys.Product;
import java.util.List;

/**
 *
 * @author mauri
 */

public interface IProductService {

    List<ProductDTO> getProducts();
    List<Product> getProductsByStatus(boolean status);
    Product findProduct(int id);
    String deleteProduct(int id);
    String updateProduct(Product product);
    String saveProduct(Product product);
    String activateProduct(int id);
    String deactivateProduct(int id);
   
    // querys
    
    //List<ProductProviderDTO> findLowStockProducts(int stock);
    
  
}
