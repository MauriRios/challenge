/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.entitys.Customer;
import java.util.List;

/**
 *
 * @author mauri
 */

public interface ICustomerService {
    
    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public void deleteCustomer(int id);
    
    public Customer findCustomer(int id);
    
}
