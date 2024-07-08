package com.productservice.productservice.controller;

import com.productservice.productservice.dto.FakeStoreProductDtos;
import com.productservice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    /*
           Total 3 ways of doing Dependency Injection.
           1. Constructoe Injection.
           2. Field Injection.
           3. Setter Injection.
    */


//    1. constructor Injection
    ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    // 2. Field Injection
//    @Autowired
//    @Qualifier("fakeStoreProductService")


    // @Autowired OPTIONAL => used to pass the object into the constructor.
    // Field Injection is not recommended always try to use the constructor Injection




    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FakeStoreProductDtos> getproductById(@PathVariable("id") Long id){
        //call the fake store product Service getProductById()

        return ResponseEntity.ok(productService.getproductById(id));
    }

    @GetMapping("/")
    public void getAllProducts(){

    }
    @DeleteMapping("/{id}")
    public void deleteProductById(){

    }

    public void createProduct(){

    }

    public void updateproductById(){

    }

}
