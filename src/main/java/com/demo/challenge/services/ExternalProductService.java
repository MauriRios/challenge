package com.demo.challenge.services;

import com.demo.challenge.dtos.ExternalProductsDTO;
import com.demo.challenge.exceptions.RequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ExternalProductService {

    public List<ExternalProductsDTO> getExternalProductsList() {
        String uri = "https://dbchallenge.onrender.com/productsList";
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap> result = restTemplate.getForObject(uri, ArrayList.class);
        if (result.isEmpty()){
            throw new RequestException("P-903","No llegan los productos externos a la api");
        }
        List<ExternalProductsDTO> products = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {

            var externalProductsList = new ExternalProductsDTO();

            externalProductsList.setName(String.valueOf(result.get(i).get("productName")));
            externalProductsList.setSku(String.valueOf(result.get(i).get("productCode")));
            externalProductsList.setStock(String.valueOf(result.get(i).get("productQuantity")));
            externalProductsList.setImgUr(String.valueOf(result.get(i).get("productImg")));

            products.add(externalProductsList);
        }
        return products;
    }
}
