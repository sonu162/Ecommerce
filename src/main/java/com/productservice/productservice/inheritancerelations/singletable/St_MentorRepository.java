package com.productservice.productservice.inheritancerelations.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface St_MentorRepository extends JpaRepository<St_Mentor, Long> {
    @Override
    <S extends St_Mentor> S save(S entity);
}
