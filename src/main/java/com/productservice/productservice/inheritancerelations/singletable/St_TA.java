package com.productservice.productservice.inheritancerelations.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_ta")
public class St_TA extends User {
    private String ta_session;
}