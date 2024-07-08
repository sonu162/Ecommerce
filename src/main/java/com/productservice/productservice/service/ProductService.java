package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDtos;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public interface ProductService {


    FakeStoreProductDtos getproductById(Long id);
    void getAllProducts();
    public void deleteProductById();
    public void createProduct();
    public void updateproductById();
}
