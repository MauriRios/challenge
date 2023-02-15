/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dto.ProductDTO;
import com.demo.challenge.dto.ProductListDTO;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.exceptions.BusinessException;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.servicesInterfaces.IProductService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author mauri
 */

@Service
public class ImpProductService implements IProductService {
    
    
        @Autowired private IProductRepository iproductRepository;

        @Autowired private IProviderService iproviderService;

        ModelMapper mapper = new ModelMapper();

        @Override
        public ProductListDTO getProducts() {
            List<Product> products = iproductRepository.findAll();
            ProductListDTO productsListDTO = mapper.map(products, ProductListDTO.class);
            return productsListDTO;
        }

        @Override
        public List<Product> getProductsByStatus(boolean status) {
        List<Product> allProducts = iproductRepository.findAll();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product: allProducts) {
            if (product.isStatus() == status) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
        }

        @Override
        public void activateProduct(int id) {
            Product product = iproductRepository.findById(id).get();
            product.setStatus(true);
            iproductRepository.save(product);
        }

        @Override
        public void deactivateProduct(int id) {
            Product product = iproductRepository.findById(id).get();
            product.setStatus(false);
            iproductRepository.save(product);
        }
        
            @Override
            public void saveProduct(Product product, int providerId) {
            try {
                if(     product.getName() == null ||
                        product.getName() == null ||
                        product.getDescription() == null ||
                        product.getPrice() <= 0 ||
                        product.getStock() <= 0 ){
                    throw new RequestException("P-400","Validacion de producto falló, todos los campos son mandatorios");
                }
                Provider provider = iproviderService.findProvider(providerId);
                
                product.setStatus(true);
                provider.getProdList().add(product);
                iproductRepository.save(product);
                 System.out.println("Producto agregado al proveedor " + providerId);
            } catch (BusinessException ex) {
                throw new BusinessException(ex.getCode(), ex.getStatus(),ex.getMessage());
            }
        }

        @Override
        public Product findProduct(int id) {
           Product product = iproductRepository.findById(id).orElse(null);
            if(product.isStatus() == true){
                return product;
            }else {
                return null;
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
        
        //query
        
        
        @Override
        public List<ProductDTO> findLowStockProducts(int stock) {
            return (List<ProductDTO>) iproductRepository.findLowStockProducts(stock).stream()
                .map(obj -> new ProductDTO(
                        obj.getName(), obj.getStock(), obj.getProviderName(), obj.getProviderPhone()));
        }


    }
