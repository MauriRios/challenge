/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

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
    public List<Customer> getCustomers() {
        return icustomerService.getCustomers();
    }
    
    @GetMapping("/traer/{id}")
    public Customer getCustomerById(@PathVariable int id) {
    Customer customer = icustomerService.findCustomer(id);
    //busco por id, verifico el status y filtro si son true
    if(customer.isStatus()){
        return customer;
    }else {
            return null;
        }
    }
    
    @GetMapping("/activo")
    public List<Customer> getActiveCustomers() {
        //trae solo los status true filtro en servImpl
            return icustomerService.getCustomersByStatus(true);
    }
    
    @PostMapping("/crear")
    public String createCustomer(@RequestBody Customer customer) {
        try {
            icustomerService.updateCustomer(customer);  
                return "Se creo el cliente correctamente"; 
        }catch (ConstraintViolationException e) {
                return "Faltan datos del cliente, por favor llena todos los campos";
        }
                  
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteCustomer(@PathVariable int id) {
        try {
                icustomerService.deleteCustomer(id);
                    return "Cliente borrado con exito";      
        } catch (EmptyResultDataAccessException ne) {        
                    return "No se encontró el cliente con id "+id;
        }   
    }

    @PutMapping("/editar/{id}")
    public Customer editCustomer (@PathVariable("id") int id,
                                        @RequestBody Customer customer)
    {
    Customer customerDB = icustomerService.findCustomer(id);
    if(customerDB.isStatus()){
        customer.setId(id);  
        icustomerService.updateCustomer(customer);
            return customer;
    }else{
            return null;
        }   
    }
    
    @PutMapping("/activo/{id}")
    public String activateCustomer(@PathVariable int id) {
        icustomerService.activateCustomer(id);
        return "El cliente ha sido activado exitosamente";
    }
    
    @PutMapping("/desactivo/{id}")
    public String deactivateCustomer(@PathVariable int id) {
        icustomerService.deactivateCustomer(id);
        return "El cliente ha sido desactivado exitosamente";
    }
}
