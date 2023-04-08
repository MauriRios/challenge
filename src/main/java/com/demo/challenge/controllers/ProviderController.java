/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controllers;

import com.demo.challenge.dtos.ProviderDTO;
import com.demo.challenge.entities.Provider;
import com.demo.challenge.servicesInterfaces.IProviderService;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
 * @author mauri
 */

@RestController
@RequestMapping("proveedor")
@CrossOrigin(origins = {"https://challenge-fe.web.app/","http://localhost:4200"})
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
    public ProviderDTO getProviderById(@PathVariable int id) {
        return iproviderService.findProviderById(id);
    }

    @GetMapping("/activos")
    public List<ProviderDTO> getActiveProviders() {
        return iproviderService.getActiveProviders(true);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> createProvider(@RequestBody Provider provider) {
        return iproviderService.createProvider(provider);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editProvider(@PathVariable("id") int id,
                                 @RequestBody Provider provider) {
        provider.setId(id);
        return iproviderService.updateProvider(provider);
    }


    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activateProvider(@PathVariable int id) {

        return iproviderService.activateProvider(id);
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> deactivateProvider(@PathVariable int id) {

        return iproviderService.deactivateProvider(id);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable int id) {
        return iproviderService.deleteProvider(id);
    }


}
