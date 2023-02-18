/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dto.LowProductProviderDTO;
import com.demo.challenge.dto.ProductDTO;
import com.demo.challenge.dto.ProductProviderDTO;
import com.demo.challenge.dto.ProviderDTO;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.servicesInterfaces.IProductService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author mauri
 */

@Service
public class ImpProductService implements IProductService {
    
    
    private final IProductRepository iproductRepository;

    public ImpProductService(IProductRepository iproductRepository) {
        this.iproductRepository = iproductRepository;

    }

    ModelMapper mapper = new ModelMapper();

        @Override
        public List<ProductDTO> getProducts() {
            var products = iproductRepository.findAll();
            List<ProductDTO> productDTO =  new ArrayList<>();
            for (var unit : products) {
                var prod = mapper.map(unit, ProductDTO.class);
                prod.setProviderName(unit.getProvider().getProviderName());
                prod.setProvideId(unit.getProvider().getId());
                productDTO.add(prod);
            }
            return productDTO;
        }

        @Override
        public List<ProductDTO> getProductsByStatus(Boolean status) {
            var allProducts = iproductRepository.findAll();
            List<ProductDTO> productDTO = new ArrayList<>();
            for (var product : allProducts) {
                if (product.getStatus() == true) {
                    var filteredProducts = mapper.map(product, ProductDTO.class);

                    filteredProducts.setProviderName(product.getProvider().getProviderName());
                    filteredProducts.setProvideId(product.getProvider().getId());
                    productDTO.add(filteredProducts);
                }
            }
            return productDTO;
        }

        @Transactional
        @Override
        public String activateProduct(int id) {
            Product product = iproductRepository.findById(id).get();
            product.setStatus(true);
            iproductRepository.save(product);
            return "El producto ha sido activado exitosamente";
        }
        @Transactional
        @Override
        public String deactivateProduct(int id) {
            Product product = iproductRepository.findById(id).get();
            product.setStatus(false);
            iproductRepository.save(product);
            return "El producto ha sido desactivado exitosamente";
        }
        @Transactional
        @Override
        public String saveProduct(Product product) {

                if(     product.getName() == null ||
                        product.getName() == null ||
                        product.getDescription() == null ||
                        product.getPrice() <= 0 ||
                        product.getStock() <= 0 ){
                    throw new RequestException("P-400","Validacion de producto falló, todos los campos son mandatorios");
                }
                iproductRepository.save(product);
                return "Producto agregado con exíto";
            }


        @Override
        public ProductDTO findProductById(int id) {
            Optional<Product> product = iproductRepository.findById(id);
            ProductDTO productDTO = mapper.map(product, ProductDTO.class);

            productDTO.setProvideId(product.get().getProvider().getId());

            return productDTO;
         }
        @Transactional
        @Override
        public String updateProduct(Product product) {
            iproductRepository.save(product);
            return "Producto editado exítosamente";
        }

        @Transactional
        @Override
        public String deleteProduct(int id) {
            try {
                iproductRepository.deleteById(id);
                return "Producto borrado con exito";
            } catch(EmptyResultDataAccessException ne) {
                return "No se encontró el producto con id " + id;
            }

        }
        
        //query
        @Override
        public List<LowProductProviderDTO> findProductsByLowStock(Integer stock){
            var products = iproductRepository.findProductsByLowStock(stock);
            List<LowProductProviderDTO> productDTO = new ArrayList<>();
            for (var unit : products) {
                var product = mapper.map(unit, LowProductProviderDTO.class);

                product.setProvider(mapper.map(unit.getProvider(), ProductProviderDTO.ProviderInfoDTO.class));
                productDTO.add(product);
            }
            return productDTO;

        }

    }
