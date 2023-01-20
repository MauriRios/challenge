/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.entitys.Product;
import java.util.List;

/**
 *
 * @author mauri
 */

public interface IProductService {
    

    public List<Product> getProducts();

    public void saveProduct(Product product, int providerId); 
    
    public void updateProduct(Product product);

    public void deleteProduct(int id);    

    public Product findProduct(int id);
  
}
