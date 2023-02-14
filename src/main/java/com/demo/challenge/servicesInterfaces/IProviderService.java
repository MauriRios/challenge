/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dto.SaleDTO;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *
 * @author mauri
 */

public interface IProviderService {
    
    
    List<Provider> getProviders();
    List<Provider> getProvidersByStatus(boolean status);
    Provider findProvider(int id);
    void deleteProvider(int id);
    void updateProvider(Provider provider);
    ResponseEntity<String> createProvider(Provider provider);
    void activateProvider(int id);
    void deactivateProvider(int id);

    List<SaleDTO> findSaleByProvider(int providerId);
    
}
