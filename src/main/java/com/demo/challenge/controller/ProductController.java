/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.dto.ProductDTO;
import com.demo.challenge.dto.ProductListDTO;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.servicesInterfaces.IProductService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    
    @GetMapping("/traer")
    public List<ProductDTO> getProducts() {
        return iproductService.getProducts();
    }
    
    @GetMapping("/traer/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return iproductService.findProductById(id);
    }
    
    @GetMapping("/activos")
    public List<ProductDTO> getActiveProducts() {
        return iproductService.getProductsByStatus(true);
    }

    @PostMapping("/crear")
    public void createProduct(@RequestBody Product product) {
        iproductService.saveProduct(product);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteProduct(@PathVariable int id) {

       return iproductService.deleteProduct(id);

    }

    @PutMapping("/editar/{id}")
    public Product editProduct(@PathVariable("id") int id,
                               @RequestBody Product product) {
        product.setId(id);
        iproductService.updateProduct(product);
        return product;
    }
    
       @PutMapping("/activo/{id}")
        public void activateProduct(@PathVariable int id) {
        iproductService.activateProduct(id);
    }
    
    @PutMapping("/desactivo/{id}")
        public void deactivateProduct(@PathVariable int id) {
        iproductService.deactivateProduct(id);
    }
        
        //query
        
//        @GetMapping("/low-stock")
//        public List<ProductProviderDTO> getLowStockProducts(@RequestParam("stock") int stock) {
//              return iproductService.findLowStockProducts(stock);
//
//
//    }
        
}
