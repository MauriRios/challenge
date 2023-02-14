package com.demo.challenge.dto;

import com.demo.challenge.entitys.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Data
public class SaleDTO {

    private int id;

    private LocalDate date;

    private int quantity;

    private double total;

    private List<Product> products;


    public SaleDTO(int id, LocalDate date, int quantity, double total, List<Product> products) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.total = total;
        this.products = products = new ArrayList<>();

    }
}
