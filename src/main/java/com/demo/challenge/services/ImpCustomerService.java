/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;


import com.demo.challenge.entitys.Customer;
import com.demo.challenge.repository.ICustomerRepository;
import com.demo.challenge.servicesInterfaces.ICustomerService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author mauri
 */

@Service
public class ImpCustomerService implements ICustomerService {
    
    @Autowired ICustomerRepository icustomerRepository;
    
    @Override
    public List<Customer> getCustomers() {
        List<Customer> customer = icustomerRepository.findAll();
        return customer;
    }
    
    @Override
    public List<Customer> getCustomersByStatus(boolean status) {
        List<Customer> allCustomers = icustomerRepository.findAll();
        List<Customer> filteredCustomers = new ArrayList<>();
        for (Customer customer: allCustomers) {
            if (customer.isStatus() == status) {
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
    
    @Override
    public void activateCustomer(int id) {
        Customer customer = icustomerRepository.findById(id).get();
        customer.setStatus(true);
        icustomerRepository.save(customer);
    }

    @Override
    public void deactivateCustomer(int id) {
        Customer customer = icustomerRepository.findById(id).get();
        customer.setStatus(false);
        icustomerRepository.save(customer);
    }

    @Override
    public void saveCustomer(Customer customer) {
        icustomerRepository.save(customer);
        
    }

    @Override
    public void deleteCustomer(int id) {
        icustomerRepository.deleteById(id);
        
    }

    @Override
    public Customer findCustomer(int id) {
       Customer customer = icustomerRepository.findById(id).orElse(null);
        return customer;

       }
    
    @Override
    public void updateCustomer(Customer customer) {
        icustomerRepository.save(customer);
    }
    
    }
