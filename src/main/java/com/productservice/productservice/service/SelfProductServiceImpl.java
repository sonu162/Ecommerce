package com.productservice.productservice.service;

import com.productservice.productservice.dto.GenericProductDto;
import com.productservice.productservice.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductServiceImpl implements ProductService {
    @Override
    public GenericProductDto getproductById(String id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public GenericProductDto updateproductById(Long id, GenericProductDto genericProductDto) {
        return null;
    }
}
