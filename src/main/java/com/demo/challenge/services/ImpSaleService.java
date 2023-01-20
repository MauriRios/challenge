/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.entitys.Customer;
import com.demo.challenge.entitys.Sale;
import com.demo.challenge.repository.ISaleRepository;
import com.demo.challenge.servicesInterfaces.ICustomerService;
import com.demo.challenge.servicesInterfaces.ISaleService;
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
    
    @Override
    public List<Sale> getSales() {
        List<Sale> sale = isaleRepository.findAll();
        return sale;
    }
    
    @Override
    public void saveSale(Sale sale, int customerId) {
    Customer customer = icustomerService.findCustomer(customerId);
    sale.setCustomer(customer);
        isaleRepository.save(sale);
        
    }
    
    @Override
    public void updateSale(Sale sale) {
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
    
}
