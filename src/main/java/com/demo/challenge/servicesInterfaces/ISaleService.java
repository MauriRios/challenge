/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dtos.SaleDTO;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mauri
 */


public interface ISaleService {

    public List<SaleDTO> getSales();

    public String createSale(SaleDTO saleDTO);

    public SaleDTO findSaleById(int id);
    
    //querys
     List<SaleDTO> findSaleByDate(LocalDate date);
     List<SaleDTO> getSalesByProviderId(Integer providerId);

}
