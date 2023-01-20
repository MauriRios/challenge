/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;


import com.demo.challenge.entitys.Provider;
import com.demo.challenge.repository.IProviderRepository;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author mauri
 */

@Service
public class ImpProviderService implements IProviderService {
    
    @Autowired IProviderRepository iproviderRepository;
    
    public List<Provider> getProviders() {
        List<Provider> providers = iproviderRepository.findAll();
        return providers;
    }

    @Override
    public void saveProvider(Provider provider) {
        iproviderRepository.save(provider);
        
    }

    @Override
    public void deleteProvider(int id) {
        iproviderRepository.deleteById(id);
        
    }

    @Override
    public Provider findProvider(int id) {
       Provider provider = iproviderRepository.findById(id).orElse(null);
        return provider;

       }
    
    }