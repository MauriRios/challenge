/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dtos.CustomerDTO;
import com.demo.challenge.entities.Customer;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author mauri
 */

public interface ICustomerService {

    public List<CustomerDTO> getCustomers();

    public List<CustomerDTO> getActiveCustomers(boolean status);

    public Customer findCustomerById(int id);

    public String deleteCustomer(int id);

    ResponseEntity<String> createCustomer(Customer customer);

    public String updateCustomer(Customer customer);

    public String activateCustomer(int id);

    public String deactivateCustomer(int id);

}
