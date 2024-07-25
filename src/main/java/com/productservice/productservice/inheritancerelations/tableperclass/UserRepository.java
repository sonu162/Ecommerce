package com.productservice.productservice.inheritancerelations.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    @Override
    <S extends User> S save(S entity);
}
