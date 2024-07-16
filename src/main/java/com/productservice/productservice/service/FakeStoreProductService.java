package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products";

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto getproductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(getProductUrl + "/" + id, FakeStoreProductDto.class);
        //convert to generic product dto before returning
        return convertToGenericProductDto(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override 
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpEntity<Void> requestEntity = new HttpEntity<>(null);
        List<FakeStoreProductDto> response = restTemplate.exchange(
                getProductUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {}
                ).getBody();
        assert response != null;
        return response.stream()
                .map(this::convertToGenericProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();


        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(getProductUrl + "/" + id, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(getProductUrl, genericProductDto, FakeStoreProductDto.class);
        return convertToGenericProductDto(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public GenericProductDto updateproductById(Long id, GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(getProductUrl + "/" + id, HttpMethod.PUT, requestCallback, responseExtractor, genericProductDto);
        return convertToGenericProductDto(responseEntity.getBody());
    }

    private GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        return GenericProductDto.builder()
                .id(fakeStoreProductDto.getId())
                .title(fakeStoreProductDto.getTitle())
                .price(fakeStoreProductDto.getPrice())
                .category(fakeStoreProductDto.getCategory())
                .description(fakeStoreProductDto.getDescription())
                .image(fakeStoreProductDto.getImage())
                .build();
    }
}
