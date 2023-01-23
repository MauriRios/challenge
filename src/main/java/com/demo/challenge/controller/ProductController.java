/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.dto.ProductDTO;
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
    
    @Autowired
    IProviderService iproviderService;
    
    @Autowired 
    IProductRepository iproductRepository;
    
    @GetMapping("/traer")
    public List<Product> getProducts() {
        return iproductService.getProducts();
    }
    
     @GetMapping("/traer/{id}")
    public Product getProductById(@PathVariable int id) {
    Product product = iproductService.findProduct(id);
    //busco por id, verifico el status y filtro si son true
    if(product.isStatus()){
        return product;
    }else {
        return null;
        }
    }
    
    @GetMapping("/activo")
    public List<Product> getActiveProducts() {
        //trae solo los status true, filtro en servImpl
        return iproductService.getProductsByStatus(true);
    }
    

    @PostMapping("/crear/{providerId}")
    public void createProduct(@PathVariable int providerId,@RequestBody Product product) {
        iproductService.saveProduct(product, providerId);
    }
    

    @DeleteMapping("/borrar/{id}")
    public String deleteProduct(@PathVariable int id) {
        try {
        iproductService.deleteProduct(id);      
            return "Producto borrado con exito";      
        } catch(EmptyResultDataAccessException ne) {        
            return "No se encontró el producto con id "+id;
        }    
    }

    @PutMapping("/editar/{id}")
    public Product editProduct(@PathVariable("id") int id,
                                        @RequestBody Product product)
    {
    Product productDB = iproductService.findProduct(id);
    if(productDB.isStatus()){
        product.setId(id);  
        iproductService.updateProduct(product);
        return product;
    }else{
        return null;
        }   
    }
    
       @PutMapping("/activo/{id}")
        public String activateProduct(@PathVariable int id) {
        iproductService.activateProduct(id);
            return "El producto ha sido activado exitosamente";
    }
    
    @PutMapping("/desactivo/{id}")
        public String deactivateProduct(@PathVariable int id) {
        iproductService.deactivateProduct(id);
            return "El producto ha sido desactivado exitosamente";
    }
        
        //query
        
        @GetMapping("/low-stock")
        public List<ProductDTO> getLowStockProducts(@RequestParam("stock") int stock) {
              return iproductService.findLowStockProducts(stock);

                   
    }
        
}
