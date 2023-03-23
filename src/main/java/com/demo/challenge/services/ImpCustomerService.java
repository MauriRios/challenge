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

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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
    JSONObject response = new JSONObject();


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

            response.put("message", "Cliente agregado con éxito");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        } catch (DataIntegrityViolationException ex) {
            throw new RequestException("P-702","El DNI/Telefono ingresado ya existe");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> updateCustomer(Customer customer) {
        try {
            if (    customer.getName() == null ||
                    customer.getLastName() == null ||
                    customer.getAddress() == null ||
                    customer.getPhone() == 0
            ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new RequestException("P-700","Validacion de Cliente falló, todos los campos son mandatorios").toString());
            }
            customer.setStatus(true);
            icustomerRepository.save(customer);
            response.put("message", "Cliente editado con éxito");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        } catch (DataIntegrityViolationException ex) {
            throw new RequestException("P-702","El DNI/Telefono ingresado ya existe");
        } catch (RequestException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> activateCustomer(int id) {
        try {
            Customer customer = icustomerRepository.findById(id).get();
            if (customer.getId() != 0){
                customer.setStatus(true);
                icustomerRepository.save(customer);
            }
            response.put("message", "Cliente ha sido activado exitosamente");
            return ResponseEntity.ok(response.toString());
        } catch (RuntimeException ex) {
            throw new RequestException("P-701","ID del Cliente faltante o incorrecto");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> deactivateCustomer(int id) {
        try {
            Customer customer = icustomerRepository.findById(id).get();
            if (customer.getId() != 0) {
                customer.setStatus(false);
                icustomerRepository.save(customer);
            }
            response.put("message", "Cliente ha sido desactivado exitosamente");
            return ResponseEntity.ok(response.toString());
        } catch (RuntimeException ex) {
            throw new RequestException("P-701","ID del Cliente faltante o incorrecto");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> deleteCustomer(int id) {
        try
        {
            icustomerRepository.deleteById(id);
            response.put("message", "Cliente ha sido Eliminado exitosamente");
            return ResponseEntity.ok(response.toString());
        } catch (EmptyResultDataAccessException ex){
            throw new RequestException("P-701", "ID del Cliente faltante o incorrecto");
        }
    }


}
