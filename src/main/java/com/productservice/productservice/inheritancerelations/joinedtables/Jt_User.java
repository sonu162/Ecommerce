package com.productservice.productservice.inheritancerelations.joinedtables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class Jt_User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String emailId;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", emailId='" + emailId + '\'' + '}';
    }
}