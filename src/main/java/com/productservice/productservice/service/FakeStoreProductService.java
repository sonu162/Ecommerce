package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericProductDto;
import com.productservice.productservice.exception.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products";


    private final Map<String, String> body;

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, Map<String, String> body){
        this.restTemplateBuilder = restTemplateBuilder;
        this.body = body;
    }

    @Override
    public GenericProductDto getproductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(getProductUrl + "/" + id, FakeStoreProductDto.class);

        // If product not found throw your own custom exception
        if(responseEntity.getBody() == null){
            throw new ProductNotFoundException("Product with id "+ id + " is not Available ");
        }

        //convert to generic product dto before returning
        return convertToGenericProductDto(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);
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
