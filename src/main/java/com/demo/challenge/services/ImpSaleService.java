/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.entitys.Customer;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.entitys.Sale;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.repository.ISaleRepository;
import com.demo.challenge.servicesInterfaces.ICustomerService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import com.demo.challenge.servicesInterfaces.ISaleService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
                //Creo nuevo array para llenarlo en el bucle
                List<Product> productList = new ArrayList<>();
                //creo variable total para llenarla en el bucle
                double total = 0.0;
                //formateo la date para luego setear el tiempo al ejecutar la venta
                LocalDate today = LocalDate.now();
                    
                    //desestructuro el array de objetos que llega de ventas y lo itero
                    //para luego restar el stock, sumar el valor total y setear la date y mandarlo a la bd
                 for(var unit: sale.getProducts()){
                     var producto = iproductRepository.findById( unit.getId()).get();
                     var quantity = producto.getStock() - unit.getQuantity();
                    
                    //cuenta da mas de lo debido, arreglar
                    total += unit.getQuantity() * unit.getPrice();
                    producto.setStock(quantity);
                    productList.add(producto);
                       }
                     //seteo valores
                         sale.setDate(today);
                         sale.setProducts(productList);
                         sale.setTotal(total);
                     //mando a la db
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

                public List<Sale> findByProviderId(int providerId) {
                    return isaleRepository.findByProviderId(providerId);
                }

    
}
