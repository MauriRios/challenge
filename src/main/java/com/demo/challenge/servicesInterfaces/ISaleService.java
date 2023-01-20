/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.entitys.Sale;
import java.util.List;

/**
 *
 * @author mauri
 */


public interface ISaleService {

    public List<Sale> getSales();

    public void saveSale(Sale sale, int customerId);
    
    public void updateSale(Sale sale);    

    public void deleteSale(int id);    

    public Sale findSale(int id);
}
