package com.productservice.productservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}