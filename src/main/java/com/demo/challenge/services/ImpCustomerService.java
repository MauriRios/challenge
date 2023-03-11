/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;


import com.demo.challenge.dtos.CustomerDTO;
import com.demo.challenge.entities.Customer;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repositories.ICustomerRepository;
import com.demo.challenge.servicesInterfaces.ICustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
public class ImpCustomerService implements ICustomerService {

    private final ICustomerRepository icustomerRepository;

    public ImpCustomerService(ICustomerRepository icustomerRepository) {
        this.icustomerRepository = icustomerRepository;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<CustomerDTO> getCustomers() {
        var customer = icustomerRepository.findAll();
        if (customer.isEmpty()){
            throw new RequestException("P-705","No hay clientes cargados");
        }
        List<CustomerDTO> customerDTO = new ArrayList<>();
        for (var unit : customer) {
            var custom = mapper.map(unit, CustomerDTO.class);

            customerDTO.add(custom);
        }
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getActiveCustomers(boolean status) {
        var allCustomers = icustomerRepository.findAll();
        if (allCustomers.isEmpty()){
            throw new RequestException("P-703","No hay clientes activos");
        }
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
    public CustomerDTO findCustomerById(int id) {
        try {
            Customer customer = icustomerRepository.findById(id).get();
            if (customer.getId() != 0){

                CustomerDTO customerDTO = (mapper.map(customer, CustomerDTO.class));
                return customerDTO;
            }

        } catch (NoSuchElementException ex) {
            throw new RequestException("P-701", "ID del Cliente faltante o incorrecto");
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<String> createCustomer(Customer customer) {
        try {
            if (    customer.getName() == null ||
                    customer.getLastName() == null ||
                    customer.getAddress() == null ||
                    customer.getPhone() == 0
                    ) {
                throw new RequestException("P-700","Validacion de Cliente falló, todos los campos son mandatorios");
            }
            customer.setStatus(true);
            icustomerRepository.save(customer);
            return ResponseEntity.ok().body("Cliente agregado con éxito");

        } catch (DataIntegrityViolationException ex) {
            throw new RequestException("P-702","El DNI/Telefono ingresado ya existe");
        }
    }

    @Transactional
    @Override
    public String updateCustomer(Customer customer) {
        try {
            if (    customer.getName() == null ||
                    customer.getLastName() == null ||
                    customer.getAddress() == null ||
                    customer.getPhone() == 0 ||
                    customer.getPhone() == 0
            ) {
                throw new RequestException("P-700","Validacion de Cliente falló, todos los campos son mandatorios");
            }
            customer.setStatus(true);
            icustomerRepository.save(customer);
            return "Cliente agregado con éxito";

        } catch (DataIntegrityViolationException ex) {
            throw new RequestException("P-702","El DNI/Telefono ingresado ya existe");
        }
    }

    @Transactional
    @Override
    public String activateCustomer(int id) {
        try {
            Customer customer = icustomerRepository.findById(id).get();
            if (customer.getId() != 0){
                customer.setStatus(true);
                icustomerRepository.save(customer);
            }
            return "Cliente ha sido activado exitosamente";
        } catch (RuntimeException ex) {
            throw new RequestException("P-701","ID del Cliente faltante o incorrecto");
        }
    }

    @Transactional
    @Override
    public String deactivateCustomer(int id) {
        try {
            Customer customer = icustomerRepository.findById(id).get();
            if (customer.getId() != 0) {
                customer.setStatus(false);
                icustomerRepository.save(customer);
            }
            return "Cliente ha sido desactivado exitosamente";
    } catch (RuntimeException ex) {
        throw new RequestException("P-701","ID del Cliente faltante o incorrecto");
        }
    }

    @Transactional
    @Override
    public String deleteCustomer(int id) {
        try
        {
            icustomerRepository.deleteById(id);
            return "Cliente borrado con exíto";
        } catch (EmptyResultDataAccessException ex){
            throw new RequestException("P-701", "ID del Cliente faltante o incorrecto");
        }
    }


}
