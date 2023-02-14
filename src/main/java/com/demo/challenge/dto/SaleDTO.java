package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class SaleDTO {

    private int id;

    private LocalDate date;

    private int quantity;

    private double total;



}
