package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDtos;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public FakeStoreProductDtos getproductById(Long id) {
        return null;
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
