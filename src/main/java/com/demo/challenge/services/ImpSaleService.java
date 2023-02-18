/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dto.*;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Sale;
import com.demo.challenge.repository.ICustomerRepository;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.repository.IProviderRepository;
import com.demo.challenge.repository.ISaleRepository;
import com.demo.challenge.servicesInterfaces.ISaleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author mauri
 */

@Service
public class ImpSaleService implements ISaleService {
    

    private final ISaleRepository isaleRepository;
    private final IProviderRepository iproviderRepository;
    private final ICustomerRepository icustomerRepository;
    private final IProductRepository iproductRepository;

    public ImpSaleService(ISaleRepository isaleRepository, IProviderRepository iproviderRepository, ICustomerRepository icustomerRepository, IProductRepository iproductRepository) {
        this.isaleRepository = isaleRepository;
        this.iproviderRepository = iproviderRepository;
        this.icustomerRepository = icustomerRepository;
        this.iproductRepository = iproductRepository;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
     public List<SaleDTO> getSales() {
         var sales = isaleRepository.findAll();
         List<SaleDTO> saleDTO = new ArrayList<>();

         for (var unit : sales){
              var purchases = mapper.map(unit, SaleDTO.class);
             purchases.setCustomerId(unit.getCustomer().getId());
             purchases.setProviderId(unit.getProvider_id().getId());
             purchases.setTotalPrice(unit.getTotalPrice());
             saleDTO.add(purchases);
         }

                return saleDTO;
     }
     @Transactional
     @Override
     public String createSale(SaleDTO saleDTO) {
        var customer = icustomerRepository.findById(saleDTO.getCustomerId()).get();
        var provider = iproviderRepository.findById(saleDTO.getProviderId()).get();
        LocalDate today = LocalDate.now();
        List<Product> products = new ArrayList<>();

        int totalQuantity = 0;
        BigDecimal totalPrice = new BigDecimal("0.0");

        for (var unit : saleDTO.getProducts()) {
             var product = iproductRepository.findById(unit.getId()).get();

             if (product.getStock() >= unit.getQuantity()) {
                 var result = product.getStock() - unit.getQuantity();

                 product.setStock(result);
                 totalQuantity += unit.getQuantity();
                 totalPrice = totalPrice.add(unit.getPrice().multiply(new BigDecimal(unit.getQuantity())));

                 product.setQuantity(unit.getQuantity());
                 products.add(mapper.map(unit, Product.class));

             }
             else {
                 return "Producto sin Stock, vuelva mas tarde";
             }
                iproductRepository.save(product);
        }

         saleDTO.setDate(today);
         saleDTO.setTotalPrice(totalPrice);
         var sale = mapper.map(saleDTO, Sale.class);
         sale.setCustomer(customer);
         sale.setProvider_id(provider);
         sale.setProducts(products);
         sale.setTotalPrice(totalPrice);
         sale.setQuantity(totalQuantity);
         isaleRepository.save(sale);
         return "Venta realizada con Exíto";
    }

    @Override
    public SaleDTO findSaleById(int id) {
        Optional<Sale> sale = isaleRepository.findById(id);
        SaleDTO saleDTO = (mapper.map(sale, SaleDTO.class));
        return saleDTO;
    }

    //querys

    @Override
    public List<SaleDTO> findSaleByDate(LocalDate date) {
        List<Sale> sales = isaleRepository.findSaleByDate(date);
        List<SaleDTO> saleDTO =  new ArrayList<>();
        for(var unit: sales) {
            saleDTO.add(mapper.map(unit, SaleDTO.class));
        }
        return saleDTO;
    }

    @Override
    public List<SaleDTO> getSalesByProviderId(Integer providerId) {
        List<Sale> sales = isaleRepository.getSalesByProviderId(providerId);
        List<SaleDTO> saleDTO =  new ArrayList<>();
        for(var unit: sales) {
            saleDTO.add(mapper.map(unit, SaleDTO.class));
        }
        return saleDTO;
    }


    
}
