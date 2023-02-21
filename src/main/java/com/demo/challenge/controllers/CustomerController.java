/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controllers;

import com.demo.challenge.dtos.CustomerDTO;
import com.demo.challenge.entities.Customer;
import com.demo.challenge.servicesInterfaces.ICustomerService;

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
@RequestMapping("cliente")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final ICustomerService icustomerService;

    public CustomerController(ICustomerService icustomerService) {
        this.icustomerService = icustomerService;
    }

    @GetMapping("/traer")
    public List<CustomerDTO> getCustomers() {
        return icustomerService.getCustomers();
    }

    @GetMapping("/traer/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        return icustomerService.findCustomerById(id);
    }

    @GetMapping("/activos")
    public List<CustomerDTO> getActiveCustomers() {
        return icustomerService.getActiveCustomers(true);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {

        return  icustomerService.createCustomer(customer);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteCustomer(@PathVariable int id) {

        return icustomerService.deleteCustomer(id);
    }

    @PutMapping("/editar/{id}")
    public Customer editCustomer(@PathVariable("id") int id,
                                 @RequestBody Customer customer) {
        customer.setId(id);
        icustomerService.updateCustomer(customer);
        return customer;
    }

    @PutMapping("/activar/{id}")
    public String activateCustomer(@PathVariable int id) {
        return icustomerService.activateCustomer(id);
    }

    @PutMapping("/desactivar/{id}")
    public String deactivateCustomer(@PathVariable int id) {
        return icustomerService.deactivateCustomer(id);
    }
}
