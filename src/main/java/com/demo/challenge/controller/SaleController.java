/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.controller;

import com.demo.challenge.entitys.Sale;
import com.demo.challenge.servicesInterfaces.ISaleService;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauri
 */

@RestController
@RequestMapping  ("venta")
@CrossOrigin(origins = "*")
public class SaleController {
    
    @Autowired 
    ISaleService isaleService;
    
    
    @GetMapping("/traer")
    public List<Sale> getSales() {
        return isaleService.getSales();
    }
    
    @GetMapping("/traer/{id}")
    public Sale getSaleById(@PathVariable int id) {
        return isaleService.findSale(id);
    }
    
    @PostMapping("/crear/{customerId}")
    public String createSale(@PathVariable int customerId, @RequestBody Sale sale) {
    isaleService.saveSale(sale, customerId);
        return "Venta realizada con éxito";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteSale(@PathVariable int id) {
        try {
        isaleService.deleteSale(id);
                return "Proveedor borrado con éxito ";
        } catch(EmptyResultDataAccessException ne) {           
            return "No se encontró el proveedor con id "+id;
        }
    }

    @PutMapping("/editar/{id}")
    public Sale editSale (@PathVariable("id") int id,
                                        @RequestBody Sale sale)
    {
    sale.setId(id);  
    isaleService.updateSale(sale);
    
    return sale;
    }  
    
    //querys
        @GetMapping("/date")
        public ResponseEntity<List<Sale>> findByDate(@RequestParam("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
            
            List<Sale> sales = isaleService.findByDate(date);
            return new ResponseEntity<>(sales, HttpStatus.OK);
        }
    
}
