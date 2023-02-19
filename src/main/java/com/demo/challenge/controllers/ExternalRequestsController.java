package com.demo.challenge.controllers;

import com.demo.challenge.dtos.ExternalProductsDTO;
import com.demo.challenge.services.ExternalProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("external")
@CrossOrigin(origins = "*")
public class ExternalRequestsController {

    private final ExternalProductService externalProductService;

    public ExternalRequestsController(ExternalProductService externalProductService) {
        this.externalProductService = externalProductService;
    }

    @GetMapping("/products")
    public List<ExternalProductsDTO> getExternalProducts(){
    return externalProductService.getExternalProductsList();
    }

}
