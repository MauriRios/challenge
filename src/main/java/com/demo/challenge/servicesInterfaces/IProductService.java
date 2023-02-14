/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dto.ProductDTO;
import com.demo.challenge.dto.ProductListDTO;
import com.demo.challenge.entitys.Product;
import java.util.List;

/**
 *
 * @author mauri
 */

public interface IProductService {
    
   ProductListDTO getProducts();
    List<Product> getProductsByStatus(boolean status);
    Product findProduct(int id);
    void deleteProduct(int id);
    void updateProduct(Product product);
    public void saveProduct(Product product, int providerId); 
    void activateProduct(int id);
    void deactivateProduct(int id);
   
    // querys
    
    List<ProductDTO> findLowStockProducts(int stock);
    
  
}
