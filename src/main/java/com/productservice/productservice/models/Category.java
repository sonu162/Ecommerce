package com.productservice.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "category")
public class Category extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
