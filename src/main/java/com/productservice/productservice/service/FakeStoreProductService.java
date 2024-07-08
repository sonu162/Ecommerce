package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDtos;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products/1";

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public FakeStoreProductDtos getproductById(Long id) {
        // Integrate the FakeStore API
        // RestTemplate
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDtos> responseEntity =
                restTemplate.getForEntity(getProductUrl, FakeStoreProductDtos.class);

        return responseEntity.getBody();
    }

    @Override 
    public void getAllProducts() {

    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateproductById() {

    }
}
