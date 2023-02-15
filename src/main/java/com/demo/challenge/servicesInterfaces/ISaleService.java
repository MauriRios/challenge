/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dto.SaleDTO;
import com.demo.challenge.entitys.Sale;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mauri
 */


public interface ISaleService {

    public List<Sale> getSales();

    public void saveSale(Sale sale, int customerId, Integer providerId);
    
    //void saveSale(Sale sale, List<Product> products, int customerId) throws IllegalArgumentException;


    public void deleteSale(int id);    

    public Sale findSale(int id);
    
    //querys
     List<Sale> findByDate(LocalDate date);


}
