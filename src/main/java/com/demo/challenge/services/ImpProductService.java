/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.servicesInterfaces.IProductService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mauri
 */

@Service
public class ImpProductService implements IProductService {
    
    
    @Autowired private IProductRepository iproductRepository;
    
    @Autowired private IProviderService iproviderService;
    
    @Override
    public List<Product> getProducts() {
        List<Product> products = iproductRepository.findAll();
        return products;
    }
    
    @Override
    public Product findProduct(int id) {
       Product product = iproductRepository.findById(id).orElse(null);
        return product;
    }

    @Override
    public void saveProduct(Product product, int providerId) {
       
        try {
        Provider provider = iproviderService.findProvider(providerId);
        provider.addProduct(product);
        iproductRepository.save(product);
         } catch (EntityNotFoundException e) {
        System.out.println("No se encontró el proveedor con id " + providerId);
        }
        
    }
    
    @Override
    public void updateProduct(Product product) {
        iproductRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        iproductRepository.deleteById(id);
        
    }


}
