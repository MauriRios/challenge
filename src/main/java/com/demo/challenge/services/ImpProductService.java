/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dtos.LowProductProviderDTO;
import com.demo.challenge.dtos.ProductDTO;
import com.demo.challenge.dtos.ProductProviderDTO;
import com.demo.challenge.entities.Product;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repositories.IProductRepository;
import com.demo.challenge.servicesInterfaces.IProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author mauri
 */

@Service
public class ImpProductService implements IProductService {


    private final IProductRepository iproductRepository;

    public ImpProductService(IProductRepository iproductRepository) {
        this.iproductRepository = iproductRepository;

    }

    ModelMapper mapper = new ModelMapper();

    JSONObject response = new JSONObject();

    @Override
    public List<ProductDTO> getProducts() {
        var products = iproductRepository.findAll();
        if (products.isEmpty()){
            throw new RequestException("P-405","No hay productos cargados");
        }
        List<ProductDTO> productDTO = new ArrayList<>();
        for (var unit : products) {
            var prod = mapper.map(unit, ProductDTO.class);
            prod.setProviderName(unit.getProvider().getProviderName());
            prod.setProvideId(unit.getProvider().getId());
            productDTO.add(prod);
        }
        return productDTO;
    }

    @Override
    public List<ProductDTO> getActiveProducts(Boolean status) {
        var allProducts = iproductRepository.findAll();
        if (allProducts.isEmpty()){
            throw new RequestException("P-403","No hay productos activos");
        }
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
    public ResponseEntity<String> saveProduct(Product product) {
        try {
            if (    product.getName() == null ||
                    product.getDescription() == null ||
                    product.getStock() <= 0 ||
                    product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RequestException("P-400", "Validacion de producto falló, todos los campos son requeridos");
            }
            iproductRepository.save(product);
            response.put("message", "Producto agregado con exíto");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            throw new RequestException("P-601", "ID del Proveedor faltante o incorrecto");
        }
    }


    @Override
    public ProductDTO findProductById(int id) {
        try {
            Optional<Product> product = iproductRepository.findById(id);
            if (product.get().getId() != null){
                ProductDTO productDTO = mapper.map(product, ProductDTO.class);
                productDTO.setProvideId(product.get().getProvider().getId());
                return productDTO;
            }
        }catch (NoSuchElementException ex) {
            throw new RequestException("P-401", "ID del Producto faltante o incorrecto");
        }
       return null;
    }

    @Transactional
    @Override
    public ResponseEntity<String> updateProduct(Product product) {
        try {
            if (    product.getName() == null ||
                    product.getName() == null ||
                    product.getDescription() == null ||
                    product.getStock() <= 0 ||
                    product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RequestException("P-400", "Validacion de producto falló, todos los campos son requeridos");
            }
            iproductRepository.save(product);
            response.put("message", "Producto editado con exíto");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        } catch (DataIntegrityViolationException ex) {
            throw new RequestException("P-601", "ID del Proveedor faltante o incorrecto");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> activateProduct(int id) {
        try {
            Product product = iproductRepository.findById(id).get();
            if (product.getId() != 0) {
                product.setStatus(true);
                iproductRepository.save(product);
            }
            response.put("message", "El producto ha sido activado exitosamente");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        }catch (RuntimeException ex) {
            throw new RequestException("P-401", "ID del Producto faltante o incorrecto");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> deactivateProduct(int id) {
        try {
            Product product = iproductRepository.findById(id).get();
            if (product.getId() != 0) {
                product.setStatus(false);
                iproductRepository.save(product);
            }
            response.put("message", "El producto ha sido desactivado exitosamente");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (RuntimeException ex) {
            throw new RequestException("P-401", "ID del Producto faltante o incorrecto");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> deleteProduct(int id) {
        try {
            iproductRepository.deleteById(id);
            response.put("message", "Producto borrado con exíto");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (EmptyResultDataAccessException ne) {
            throw new RequestException("P-401", "ID del Producto faltante o incorrecto");
        }
    }

    //query
    @Override
    public List<LowProductProviderDTO> findProductsByLowStock(Integer stock) {
            var products = iproductRepository.findProductsByLowStock(stock);

                if (products.isEmpty()) {
                    throw new RequestException("P-402","No hay productos con stock por debajo de " +stock);
                }
                List<LowProductProviderDTO> productDTO = new ArrayList<>();
                for (var unit : products) {
                    var product = mapper.map(unit, LowProductProviderDTO.class);

                    product.setProvider(mapper.map(unit.getProvider(), ProductProviderDTO.ProviderInfoDTO.class));
                    productDTO.add(product);
                }
                return productDTO;

    }

}
