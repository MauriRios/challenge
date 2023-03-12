package com.demo.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String name;
    private String lastName;
    private Integer dni;
    private Integer phone;
    private String address;
    private Boolean status;
    private List<CustomerSaleDTO> purchases;

}
