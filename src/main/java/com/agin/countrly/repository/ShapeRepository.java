package com.agin.countrly.repository;

import com.agin.countrly.entity.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {
    Optional<Shape> findByCountryId(Long countryId);
}
