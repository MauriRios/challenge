/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mauri
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    private String name;
    private int stock;
    private String providerName;
    private String providerPhone;
    
    
}
