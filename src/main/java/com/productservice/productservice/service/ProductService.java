package com.productservice.productservice.service;

import com.productservice.productservice.dto.GenericProductDto;
import com.productservice.productservice.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    GenericProductDto getproductById(String id) throws ProductNotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id);

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto updateproductById(Long id, GenericProductDto genericProductDto);
}
