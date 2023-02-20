/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dtos.CustomerDTO;
import com.demo.challenge.entities.Customer;

import java.util.List;

/**
 * @author mauri
 */

public interface ICustomerService {

    List<CustomerDTO> getCustomers();

    List<CustomerDTO> getCustomersByStatus(boolean status);

    Customer findCustomer(int id);

    void deleteCustomer(int id);

    void updateCustomer(Customer customer);

    void activateCustomer(int id);

    void deactivateCustomer(int id);

}
