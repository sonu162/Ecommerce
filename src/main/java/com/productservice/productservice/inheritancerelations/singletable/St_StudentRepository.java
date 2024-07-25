package com.productservice.productservice.inheritancerelations.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface St_StudentRepository extends JpaRepository<St_Student, Long> {
    @Override
    <S extends St_Student> S save(S entity);
}
