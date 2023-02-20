/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;


import com.demo.challenge.dtos.CustomerDTO;
import com.demo.challenge.entities.Customer;
import com.demo.challenge.repositories.ICustomerRepository;
import com.demo.challenge.servicesInterfaces.ICustomerService;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @author mauri
 */

@Service
public class ImpCustomerService implements ICustomerService {

    private final ICustomerRepository icustomerRepository;

    public ImpCustomerService(ICustomerRepository icustomerRepository) {
        this.icustomerRepository = icustomerRepository;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<CustomerDTO> getCustomers() {
        var customer = icustomerRepository.findAll();
        List<CustomerDTO> customerDTO = new ArrayList<>();
        for (var unit : customer) {
            var custom = mapper.map(unit, CustomerDTO.class);

            customerDTO.add(custom);
        }
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getCustomersByStatus(boolean status) {
        var allCustomers = icustomerRepository.findAll();
        List<CustomerDTO> customerDTO = new ArrayList<>();
        for (var customer : allCustomers) {
            if (customer.getStatus() == true) {
                var filteredCustomer = mapper.map(customer, CustomerDTO.class);

                customerDTO.add(filteredCustomer);
            }
        }
        return customerDTO;
    }

    @Override
    public Customer findCustomer(int id) {
        Customer customer = icustomerRepository.findById(id).orElse(null);
        return customer;

    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        customer.setStatus(true);
        icustomerRepository.save(customer);
    }

    @Transactional
    @Override
    public void activateCustomer(int id) {
        Customer customer = icustomerRepository.findById(id).get();
        customer.setStatus(true);
        icustomerRepository.save(customer);
    }

    @Transactional
    @Override
    public void deactivateCustomer(int id) {
        Customer customer = icustomerRepository.findById(id).get();
        customer.setStatus(false);
        icustomerRepository.save(customer);
    }

    @Transactional
    @Override
    public void deleteCustomer(int id) {
        icustomerRepository.deleteById(id);
    }


}
