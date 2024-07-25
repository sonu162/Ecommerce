package com.productservice.productservice.inheritancerelations.joinedtables;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class Jt_TA extends Jt_User {
    private String ta_session;
}