package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTO {

    private int id;
    private String providerName;
    private String providerLastName;
    private int cuit;
    private int phone;
    private String address;
    private Boolean status;
    private List<ProductDTO> ProductList;

}
