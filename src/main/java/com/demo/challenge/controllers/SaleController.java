/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controllers;

import com.demo.challenge.dtos.SaleDTO;
import com.demo.challenge.entities.OrderDetail;
import com.demo.challenge.servicesInterfaces.IOrderDetailService;
import com.demo.challenge.servicesInterfaces.IProviderService;
import com.demo.challenge.servicesInterfaces.ISaleService;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mauri
 */

@RestController
@RequestMapping("venta")
@CrossOrigin(origins = "*")
public class SaleController {


    private final ISaleService isaleService;

    public SaleController(ISaleService isaleService, IProviderService iproviderService) {
        this.isaleService = isaleService;
    }

    ModelMapper mapper = new ModelMapper();

    @GetMapping("/traer")
    public List<SaleDTO> getSales() {
        return isaleService.getSales();
    }

    @GetMapping("/traer/{id}")
    public SaleDTO getSaleById(@PathVariable int id) {

        return isaleService.findSaleById(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> createSale(@RequestBody SaleDTO saleDTO) {
        return isaleService.createSale(saleDTO);

    }

    //querys

    @GetMapping("/date")
    public List<SaleDTO> findSaleByDate(@RequestParam("date")
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return isaleService.findSaleByDate(date);
    }

    @GetMapping("/proveedor")
    public List<SaleDTO> getSalesByProviderId(@RequestParam("providerId") Integer providerId) {
        return isaleService.getSalesByProviderId(providerId);
    }

}
