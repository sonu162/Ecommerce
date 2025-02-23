package com.productservice.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product")
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Price price;
    // category is not a simple attribute, It's a relation
    @ManyToOne
    private Category category;

    @Override
    public String toString() {
        return "Product{" + "title='" + title + '\'' + ", description='" + description + '\'' + ", price=" + price
                + ", image='" + image + '\'' + ", category=" + category + '}';
    }
}

/*
 *
 * 1 N 1 1 category ----- Product
 *
 */