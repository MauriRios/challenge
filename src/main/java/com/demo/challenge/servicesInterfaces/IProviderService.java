/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dtos.ProviderDTO;
import com.demo.challenge.entities.Provider;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *
 * @author mauri
 */

public interface IProviderService {
    
    
    List<ProviderDTO> getProviders();
    List<ProviderDTO> getProvidersByStatus(boolean status);
    Provider findProvider(int id);
    void deleteProvider(int id);
    void updateProvider(Provider provider);
    ResponseEntity<String> createProvider(Provider provider);
    void activateProvider(int id);
    void deactivateProvider(int id);

    
}
