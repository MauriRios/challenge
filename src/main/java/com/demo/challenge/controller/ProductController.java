/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.entitys.Product;
import com.demo.challenge.servicesInterfaces.IProductService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauri
 */

@RestController
@RequestMapping  ("producto")
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired 
    IProductService iproductService;
    
    @Autowired
    IProviderService iproviderService;
    
    @GetMapping("/traer")
    public List<Product> getProducts() {
        return iproductService.getProducts();
    }
    
    @GetMapping("/traer/{id}")
    public Product getProductById(@PathVariable int id) {

        return iproductService.findProduct(id);
    }
    
    @PostMapping("/crear/{providerId}")
    public void createProduct(@PathVariable int providerId,@RequestBody Product product) {
        iproductService.saveProduct(product, providerId);
       
    }
    

    @DeleteMapping("/borrar/{id}")
    public void deleteProduct(@PathVariable int id) {
        iproductService.deleteProduct(id);
        
    }

    @PutMapping("/editar/{id}")
    public Product editProduct (@PathVariable("id") int id,
                                        @RequestBody Product product)
    {
    product.setId(id);  
    iproductService.updateProduct(product);
    
    return product;
    }
}
