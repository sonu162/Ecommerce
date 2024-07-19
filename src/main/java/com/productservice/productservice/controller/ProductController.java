package com.productservice.productservice.controller;

import com.productservice.productservice.dto.ExceptionDto;
import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericProductDto;
import com.productservice.productservice.exception.ProductNotFoundException;
import com.productservice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<GenericProductDto> getproductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getproductById(id));
    }

    @GetMapping()
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @PostMapping()
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto genericProductDto){
        return ResponseEntity.ok(productService.createProduct(genericProductDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericProductDto> updateproductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto){
        return ResponseEntity.ok(productService.updateproductById(id, genericProductDto));
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleProductNoFoundException(ProductNotFoundException productNotFoundException){
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage(productNotFoundException.getMessage());
//        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
//    }
}
