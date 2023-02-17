/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dto.*;
import com.demo.challenge.entitys.Customer;
import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import com.demo.challenge.entitys.Sale;
import com.demo.challenge.repository.ICustomerRepository;
import com.demo.challenge.repository.IProductRepository;
import com.demo.challenge.repository.IProviderRepository;
import com.demo.challenge.repository.ISaleRepository;
import com.demo.challenge.servicesInterfaces.ISaleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
             saleDTO.add(purchases);
         }

                return saleDTO;
     }
     @Transactional
     @Override
     public String createSale(SaleRequestDTO saleRequestDTO) {
        var customer = icustomerRepository.findById(saleRequestDTO.getCustomerId());
        var provider = iproviderRepository.findById(saleRequestDTO.getProviderId());
        LocalDate today = LocalDate.now();
        Set<ProductSaleDTO> products = new HashSet<>();

        int totalQuantity = 0;
        BigDecimal totalPrice = new BigDecimal("0.0");

        for (var unit : saleRequestDTO.getProducts()) {
             var product = iproductRepository.findById(unit.getId()).get();

             if (product.getStock() >= unit.getQuantity()) {
                 var result = product.getStock() - unit.getQuantity();

                 product.setStock(result);
                 totalQuantity += unit.getQuantity();
                 totalPrice = totalPrice.add(unit.getPrice().multiply(new BigDecimal(unit.getQuantity())));
                 products.add(mapper.map(unit, ProductSaleDTO.class));
             }
             else {
                 return "la cagaste capo, no tengo tanto stock, vuelva prontos";
             }
                iproductRepository.save(product);
        }

         saleRequestDTO.setDate(today);
         var sale = mapper.map(saleRequestDTO, Sale.class);
//         sale.setCustomer(customer);
//         sale.setProvider_id(provider);
//         sale.setProducts(products);
         sale.setTotal(totalPrice);
         isaleRepository.save(sale);
         return "Venta realizada con Exíto";

    }


        @Override
        public Sale findSale(int id) {
             Sale sale = isaleRepository.findById(id).orElse(null);
             return sale;

            }

        //querys

        public List<Sale> findByDate(LocalDate date) {
                return isaleRepository.findByDate(date);
            }


    
}
