package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericProductDto;
import com.productservice.productservice.exception.ProductNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface ProductService {


    GenericProductDto getproductById(Long id) throws ProductNotFoundException;
    List<GenericProductDto> getAllProducts();
    public GenericProductDto deleteProductById(Long id);
    public GenericProductDto createProduct(GenericProductDto genericProductDto);
    public GenericProductDto updateproductById(Long id, GenericProductDto genericProductDto);
}
