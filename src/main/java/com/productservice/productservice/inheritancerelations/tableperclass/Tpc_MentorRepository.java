package com.productservice.productservice.inheritancerelations.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tpc_MentorRepository extends JpaRepository<Tpc_Mentor, Long> {
}
