package com.productservice.productservice.inheritancerelations.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface St_UserRepository extends JpaRepository<User, Long> {
    @Override
    <S extends User> S save(S entity);
}
