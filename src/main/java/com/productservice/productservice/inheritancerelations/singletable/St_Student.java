package com.productservice.productservice.inheritancerelations.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_student")
@DiscriminatorValue(value = "Student")
public class St_Student extends User {
    private double psp;
}
