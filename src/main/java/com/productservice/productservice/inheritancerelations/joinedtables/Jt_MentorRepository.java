package com.productservice.productservice.inheritancerelations.joinedtables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Jt_MentorRepository extends JpaRepository<Jt_Mentor, Long> {
}
