package com.productservice.productservice.models;


import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "price")
public class Price extends BaseModel {
    private String currency;
    private double value;
}
