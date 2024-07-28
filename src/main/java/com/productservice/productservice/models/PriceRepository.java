package com.productservice.productservice.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, String> {
}