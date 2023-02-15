/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;


import com.demo.challenge.dto.SaleRequestDTO;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.repository.IProviderRepository;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/**
 *
 * @author mauri
 */

@Service
public class ImpProviderService implements IProviderService {
    
        @Autowired IProviderRepository iproviderRepository;

        @Override
        public List<Provider> getProviders() {
            List<Provider> providers = iproviderRepository.findAll();
            return providers;
        }
    
        @Override
        public List<Provider> getProvidersByStatus(boolean status) {
        //traigo "todos" por id creo una lista nueva y desestructuro "todos" 
        //con el ciclo for itero cada uno, con el if comparo el status si es true
        //lo guardo en la lista nueva y la retorno 
        List<Provider> allProviders = iproviderRepository.findAll();
        List<Provider> filteredProviders = new ArrayList<>();
        for (Provider provider: allProviders) {
            if (provider.getStatus() == true) {
                filteredProviders.add(provider);
            }
        }
        return filteredProviders;
        }

        @Override
        public void activateProvider(int id) {
            //busco por id y seteo el status en true
            Provider provider = iproviderRepository.findById(id).get();
            provider.setStatus(true);
            iproviderRepository.save(provider);
        }

        @Override
        public void deactivateProvider(int id) {
            //busco por id y seteo el status en false
            Provider provider = iproviderRepository.findById(id).get();
            provider.setStatus(false);
            iproviderRepository.save(provider);
        }
        
            @Override
            public ResponseEntity<String> createProvider(Provider provider) {
            try {
                if(provider.getProviderName() == null ||
                   provider.getCuit() == 0 ||
                   provider.getAddress() == null ||
                   provider.getPhone() == 0)
                {
                    throw new IllegalArgumentException("Validacion de proovedor falló, todos los campos son mandatorios");
                }
                provider.setStatus(true);
                iproviderRepository.save(provider);
                return ResponseEntity.ok().body("Proveedor agregado con éxito");
            } catch (DataIntegrityViolationException ex) {
                return ResponseEntity.badRequest().body("El CUIT ingresado ya existe en la base de datos");
            }
            }

        @Override
        public void updateProvider(Provider provider) {
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
           @Override
           public List<SaleRequestDTO> findSaleByProvider(int providerId){
            return iproviderRepository.findByProviderId(providerId);
           }

        }