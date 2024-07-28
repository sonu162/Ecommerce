package com.productservice.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Price price;

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }

    //category is not a simple attribute, It's a relation
    @ManyToOne
    private Category category;
}



/*

1                   N
1                   1
category    ----- Product

 */