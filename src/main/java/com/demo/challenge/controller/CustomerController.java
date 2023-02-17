/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.dto.CustomerDTO;
import com.demo.challenge.entitys.Customer;
import com.demo.challenge.servicesInterfaces.ICustomerService;
import java.util.List;
import javax.validation.ConstraintViolationException;
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
@RequestMapping  ("cliente")
@CrossOrigin(origins = "*")
public class CustomerController {
    
    @Autowired 
    ICustomerService icustomerService;
    
    @GetMapping("/traer")
    public List<CustomerDTO> getCustomers() {
        return icustomerService.getCustomers();
    }
    
    @GetMapping("/traer/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return icustomerService.findCustomer(id);
    }
    
    @GetMapping("/activos")
    public List<Customer> getActiveCustomers() {
            return icustomerService.getCustomersByStatus(true);
    }
    
    @PostMapping("/crear")
    public void createCustomer(@RequestBody Customer customer) {
            icustomerService.updateCustomer(customer);  

        }

    @DeleteMapping("/borrar/{id}")
    public void deleteCustomer(@PathVariable int id) {
                icustomerService.deleteCustomer(id);

//        catch (EmptyResultDataAccessException ne) {
//                    return "No se encontró el cliente con id " + id;
//        }
    }

    @PutMapping("/editar/{id}")
    public Customer editCustomer (@PathVariable("id") int id,
                                        @RequestBody Customer customer){
        customer.setId(id);
        icustomerService.updateCustomer(customer);
        return customer;
    }

    
    @PutMapping("/activo/{id}")
    public void activateCustomer(@PathVariable int id) {
        icustomerService.activateCustomer(id);
    }
    
    @PutMapping("/desactivo/{id}")
    public void deactivateCustomer(@PathVariable int id) {
        icustomerService.deactivateCustomer(id);
    }
}
