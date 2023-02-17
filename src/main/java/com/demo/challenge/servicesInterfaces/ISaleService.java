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

    public List<SaleDTO> getSales();

    public String createSale(SaleDTO saleDTO);

    public Sale findSale(int id);
    
    //querys
     List<Sale> findByDate(LocalDate date);


}
