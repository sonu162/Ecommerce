package com.productservice.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private int price;
    private String image;

    //category is not a simple attribute, It's a relation
    @ManyToOne
    private Category category;
}



/*

1                   N
1                   1
category    ----- Product

 */