/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.dto;

/**
 *
 * @author mauri
 */


public class ProductDTO {
    
    private String name;
    private int stock;
    private String providerName;
    private String providerPhone;

    public ProductDTO(String name, int stock, String providerName, String providerPhone) {
        this.name = name;
        this.stock = stock;
        this.providerName = providerName;
        this.providerPhone = providerPhone;
    }
    
    

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }
    
    
    
}
