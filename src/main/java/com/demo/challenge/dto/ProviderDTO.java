package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
