package com.productservice.productservice.thirdPartyClients.fakeStoreClient;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericProductDto;
import com.productservice.productservice.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class FakeStoreClientAdaptor {

    private final Map<String, String> body;
    private final RestTemplateBuilder restTemplateBuilder;
    private final String specificProductUrl;
    private final String genericProducturl;

    FakeStoreClientAdaptor(RestTemplateBuilder restTemplateBuilder, Map<String, String> body,
                           @Value("${fakestore.api.url}") String fakeStoreUrl,
                           @Value("${fakestore.api.paths.products}") String pathForProducts) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.body = body;
        this.specificProductUrl = fakeStoreUrl + pathForProducts + "/{id}";
        this.genericProducturl = fakeStoreUrl + pathForProducts;
    }

    public FakeStoreProductDto getproductById(String id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductUrl,
                FakeStoreProductDto.class, id);

        // If product not found throw your own custom exception
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id " + id + " is not Available ");
        }

        // convert to generic product dto before returning
        return Objects.requireNonNull(responseEntity.getBody());
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);
        List<FakeStoreProductDto> response = restTemplate.exchange(genericProducturl, HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
                }).getBody();
        assert response != null;
        return new ArrayList<>(response);
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate
                .responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(genericProducturl,
                genericProductDto, FakeStoreProductDto.class);
        return Objects.requireNonNull(responseEntity.getBody());
    }

    public FakeStoreProductDto updateproductById(Long id, GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate
                .responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.PUT,
                requestCallback, responseExtractor, genericProductDto, id);
        return responseEntity.getBody();
    }
}
