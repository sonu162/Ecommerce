package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericProductDto;
import com.productservice.productservice.exception.ProductNotFoundException;
import com.productservice.productservice.thirdPartyClients.fakeStoreClient.FakeStoreClientAdaptor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fakeStoreProductService")
// @Primary -> this will make FakeStoreProductService as a default impl of Product service in case we are not using the
// @Qualifier in controller to specifically tell about the required implementation
public class FakeStoreProductService implements ProductService {

    FakeStoreClientAdaptor fakeStoreClientAdaptor;

    public FakeStoreProductService(FakeStoreClientAdaptor fakeStoreClientAdaptor) {
        this.fakeStoreClientAdaptor = fakeStoreClientAdaptor;
    }

    @Override
    public GenericProductDto getproductById(String id) throws ProductNotFoundException {
        return convertToGenericProductDto(fakeStoreClientAdaptor.getproductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return fakeStoreClientAdaptor.getAllProducts().stream().map(this::convertToGenericProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertToGenericProductDto(fakeStoreClientAdaptor.deleteProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return convertToGenericProductDto(fakeStoreClientAdaptor.createProduct(genericProductDto));
    }

    @Override
    public GenericProductDto updateproductById(Long id, GenericProductDto genericProductDto) {
        return convertToGenericProductDto(fakeStoreClientAdaptor.updateproductById(id, genericProductDto));
    }

    private GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
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
