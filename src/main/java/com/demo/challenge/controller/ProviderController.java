/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.dto.ProviderDTO;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauri
 */

@RestController
@RequestMapping  ("proveedor")
@CrossOrigin(origins = "*")
public class ProviderController {
    

    private final IProviderService iproviderService;

    public ProviderController(IProviderService iproviderService) {
        this.iproviderService = iproviderService;
    }


    @GetMapping("/traer")
    public List<ProviderDTO> getProviders() {
        return iproviderService.getProviders();
    }
    
    @GetMapping("/traer/{id}")
    public Provider getProviderById(@PathVariable int id) {
    return iproviderService.findProvider(id);
    }
    
    @GetMapping("/activos")
    public List<ProviderDTO> getActiveProviders() {
        return iproviderService.getProvidersByStatus(true);
    }
    
    @PostMapping("/crear")
    public void createProvider(@RequestBody Provider provider) {
        iproviderService.createProvider(provider);
    }

    @DeleteMapping("/borrar/{id}")
    public void deleteProvider(@PathVariable int id) {
             iproviderService.deleteProvider(id);
        }
//        catch(EmptyResultDataAccessException ne) {
//            return "No se encontró el proveedor con id "+id;
//        }
    
    @PutMapping("/editar/{id}")
    public Provider editProvider (@PathVariable("id") int id,
                                  @RequestBody Provider provider){
        provider.setId(id);  
        iproviderService.updateProvider(provider);
        return provider;
    }

    
    @PutMapping("/activo/{id}")
    public void activateProvider(@PathVariable int id) {
        iproviderService.activateProvider(id);

    }
    
    @PutMapping("/desactivo/{id}")
    public void deactivateProvider(@PathVariable int id) {
        iproviderService.deactivateProvider(id);
    }

//    @GetMapping("/ventas/{providerId}")
//    public List<SaleRequestDTO> sales(@PathVariable int providerId) {
//
//     return iproviderService.findSaleByProvider(providerId);
//
//    }

}
