/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dto.SaleDTO;
import com.demo.challenge.entitys.Customer;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.entitys.Sale;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.repository.ISaleRepository;
import com.demo.challenge.servicesInterfaces.ICustomerService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import com.demo.challenge.servicesInterfaces.ISaleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mauri
 */

@Service
public class ImpSaleService implements ISaleService {
    
            @Autowired
            private ISaleRepository isaleRepository;

            @Autowired
            private ICustomerService icustomerService;

            @Autowired
            private IProductRepository iproductRepository;

            @Autowired
            private IProviderService iproviderService;

            @Override
            public List<Sale> getSales() {
                List<Sale> sale = isaleRepository.findAll();
                return sale;
            }

             @Override
             public void saveSale(Sale sale, int customerId, Integer providerId) {
                LocalDate today = LocalDate.now();
                List<Product> productList = new ArrayList<>();
                double total = 0.0;


                    for (var unit : sale.getProducts()) {
                        var product = iproductRepository.findById(unit.getId()).get();
                        var quantity = product.getStock() - unit.getQuantity();
                        total += unit.getQuantity() * unit.getPrice();
                        product.setStock(quantity);
                        productList.add(product);
                    }

                    sale.setDate(today);
                    sale.setProducts(productList);
                    sale.setTotal(total);

                    Customer customer = icustomerService.findCustomer(customerId);
                    sale.setCustomer(customer);
                    Provider provider = iproviderService.findProvider(providerId);
                    sale.setProvider(provider);
                    isaleRepository.save(sale);

             }

            @Override
            public void deleteSale(int id) {
                isaleRepository.deleteById(id);

            }

            @Override
            public Sale findSale(int id) {
               Sale sale = isaleRepository.findById(id).orElse(null);
                return sale;

               }

                //querys 


                public List<Sale> findByDate(LocalDate date) {
                return isaleRepository.findByDate(date);
                }

                public List<SaleDTO> findByProviderId(int providerId) {
                    return isaleRepository.findByProviderId(providerId);
                }

    
}
