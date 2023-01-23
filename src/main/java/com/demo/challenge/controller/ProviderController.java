/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.entitys.Provider;
import com.demo.challenge.servicesInterfaces.IProviderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    Provider provider = iproviderService.findProvider(id);
    //busco por id, verifico el status y filtro si son true
    if(provider.isStatus()){
        return provider;
    }else {
        return null;
        }
    }
    
    @GetMapping("/activo")
    public List<Provider> getActiveProviders() {
        //trae solo los status true filtro en servImpl
        return iproviderService.getProvidersByStatus(true);
    }
    
    @PostMapping("/crear")
    public void createProvider(@RequestBody Provider provider) {
        iproviderService.createProvider(provider);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteProvider(@PathVariable int id) {
        try {
             iproviderService.deleteProvider(id);
        return "Proveedor borrado con éxito";
        } catch(EmptyResultDataAccessException ne) {           
            return "No se encontró el proveedor con id "+id;
        }   
    }
       
    
    
    @PutMapping("/editar/{id}")
    public Provider editProvider (@PathVariable("id") int id,
                                        @RequestBody Provider provider)
    {
    Provider providerDB = iproviderService.findProvider(id);
    if(providerDB.isStatus()){
        provider.setId(id);  
        iproviderService.updateProvider(provider);
        return provider;
    }else{
        throw new IllegalArgumentException("Validacion de producto falló, todos los campos son mandatorios");
        }   
    }
    
    @PutMapping("/activo/{id}")
    public String activateProvider(@PathVariable int id) {
        iproviderService.activateProvider(id);
        return "El proveedor ha sido activado exitosamente";
    }
    
    @PutMapping("/desactivo/{id}")
    public String deactivateProvider(@PathVariable int id) {
        iproviderService.deactivateProvider(id);
        return "El proveedor ha sido desactivado exitosamente";
    }
}
