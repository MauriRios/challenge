/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;


import com.demo.challenge.dtos.ProductDTO;
import com.demo.challenge.dtos.ProviderDTO;
import com.demo.challenge.entities.Provider;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repositories.IProductRepository;
import com.demo.challenge.repositories.IProviderRepository;
import com.demo.challenge.servicesInterfaces.IProviderService;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @author mauri
 */

@Service
public class ImpProviderService implements IProviderService {

    private final IProviderRepository iproviderRepository;

    private final IProductRepository iproductRepository;

    public ImpProviderService(IProviderRepository iproviderRepository, IProductRepository iproductRepository) {
        this.iproviderRepository = iproviderRepository;
        this.iproductRepository = iproductRepository;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<ProviderDTO> getProviders() {
        var providers = iproviderRepository.findAll();
        var products = iproductRepository.findAll();
        List<ProviderDTO> providerDTO = new ArrayList<>();

        for (var unit : providers) {
            var prov = mapper.map(unit, ProviderDTO.class);
            List<ProductDTO> productDTO = new ArrayList<>();

            for (var unit2 : products) {
                if (unit2.getProvider().getId() == unit.getId()) {
                    var providerIdProduct = mapper.map(unit2, ProductDTO.class);
                    providerIdProduct.setProvideId(unit.getId());
                    productDTO.add(providerIdProduct);
                }
            }
            prov.setProductList(productDTO);
            providerDTO.add(prov);
        }

        return providerDTO;
    }


    @Override
    public List<ProviderDTO> getProvidersByStatus(boolean status) {
        var allProviders = iproviderRepository.findAll();
        var products = iproductRepository.findAll();
        List<ProviderDTO> providerDTO = new ArrayList<>();
        for (var provider : allProviders) {
            if (provider.getStatus() == true) {
                var filteredProviders = mapper.map(provider, ProviderDTO.class);
                List<ProductDTO> productDTO = new ArrayList<>();

                for (var unit2 : products) {
                    if (unit2.getProvider().getId() == filteredProviders.getId()) {
                        var providerIdProduct = mapper.map(unit2, ProductDTO.class);
                        providerIdProduct.setProvideId(filteredProviders.getId());
                        productDTO.add(providerIdProduct);
                    }
                }
                filteredProviders.setProductList(productDTO);
                providerDTO.add(filteredProviders);
            }
        }
        return providerDTO;
    }

    @Override
    public Provider findProvider(int id) {
        Provider provider = iproviderRepository.findById(id).orElse(null);
        return provider;

    }

    @Transactional
    @Override
    public ResponseEntity<String> createProvider(Provider provider) {
        try {
            if (provider.getProviderName() == null ||
                    provider.getCuit() == 0 ||
                    provider.getAddress() == null ||
                    provider.getPhone() == 0) {
                throw new RequestException("P-600","Validacion de proovedor falló, todos los campos son mandatorios");
            }
            provider.setStatus(true);
            iproviderRepository.save(provider);
            return ResponseEntity.ok().body("Proveedor agregado con éxito");

            } catch (DataIntegrityViolationException ex) {
            throw new RequestException("P-602","El CUIT/Telefono ingresado ya existe");
        }
    }


    @Transactional
    @Override
    public String updateProvider(Provider provider) {

        iproviderRepository.save(provider);
        return "Proveedor Editado exítosamente";
    }

    @Transactional
    @Override
    public String activateProvider(int id) {
        Provider provider = iproviderRepository.findById(id).get();
        provider.setStatus(true);
        iproviderRepository.save(provider);

        return "Proveedor Activado exítosamente";
    }

    @Transactional
    @Override
    public String deactivateProvider(int id) {
        Provider provider = iproviderRepository.findById(id).get();
        provider.setStatus(false);
        iproviderRepository.save(provider);

        return "Proveedor Desactivado exítosamente";
    }

    @Transactional
    @Override
    public String deleteProvider(int id) {
        try
        {
            iproviderRepository.deleteById(id);
            return "Proveedor borrado con exíto";
        } catch (EmptyResultDataAccessException ex){
            throw new RequestException("P-601", "ID del Proveedor faltante o incorrecto");
        }

    }


}