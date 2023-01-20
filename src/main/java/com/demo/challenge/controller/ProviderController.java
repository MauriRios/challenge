/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.entitys.Provider;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired 
    IProviderService iproviderService;
    
    
    @GetMapping("/traer")
    public List<Provider> getProviders() {
        return iproviderService.getProviders();
    }
    
    @GetMapping("/traer/{id}")
    public Provider getProviderById(@PathVariable int id) {
        return iproviderService.findProvider(id);
    }
    
    @PostMapping("/crear")
    public void createProvider(@RequestBody Provider provider) {
        iproviderService.saveProvider(provider);
        
    }

    @DeleteMapping("/borrar/{id}")
    public void deleteProvider(@PathVariable int id) {
        iproviderService.deleteProvider(id);
        
    }

    @PutMapping("/editar/{id}")
    public Provider editProvider (@PathVariable("id") int id,
                                        @RequestBody Provider provider)
    {
    provider.setId(id);  
    iproviderService.saveProvider(provider);
    
    return provider;
    }
}
