/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controllers;

import com.demo.challenge.dtos.LowProductProviderDTO;
import com.demo.challenge.dtos.ProductDTO;
import com.demo.challenge.entities.Product;
import com.demo.challenge.servicesInterfaces.IProductService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mauri
 */

@RestController
@RequestMapping("producto")
@CrossOrigin(origins = "*")
public class ProductController {


    private final IProductService iproductService;

    public ProductController(IProductService iproductService) {
        this.iproductService = iproductService;
    }


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
        return iproductService.getActiveProducts(true);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        return iproductService.saveProduct(product);

    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editProduct(@PathVariable("id") int id, @RequestBody Product product) {
        product.setId(id);
     return  iproductService.updateProduct(product);

    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activateProduct(@PathVariable int id) {

        return iproductService.activateProduct(id);
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> deactivateProduct(@PathVariable int id) {
        return iproductService.deactivateProduct(id);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return iproductService.deleteProduct(id);
    }

    //query

    @GetMapping("/lowStock")
    public List<LowProductProviderDTO> getLowStockProducts(@RequestParam("stock") Integer id) {
        return iproductService.findProductsByLowStock(id);
    }

}
