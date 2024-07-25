package com.productservice.productservice.inheritancerelations.joinedtables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Jt_StudentRepository extends JpaRepository<Jt_Student, Long> {

    @Override
    <S extends Jt_Student> S save(S entity);
}
