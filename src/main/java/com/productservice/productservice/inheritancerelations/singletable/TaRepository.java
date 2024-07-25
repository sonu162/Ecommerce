package com.productservice.productservice.inheritancerelations.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaRepository extends JpaRepository<St_TA, Long> {
    @Override
    <S extends St_TA> S save(S entity);
}
