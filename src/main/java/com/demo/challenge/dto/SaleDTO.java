package com.demo.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private Integer id;
    private Integer providerId;
    private Integer customerId;
    private LocalDate date;
    private Integer quantity;
    private Double totalPrice;
    private List<ProductSaleDTO> purchases =  new ArrayList<>();
}
