package com.productservice.productservice.inheritancerelations.joinedtables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Jt_UserRepository extends JpaRepository<Jt_User, Long> {

    @Override
    <S extends Jt_User> S save(S entity);
}
