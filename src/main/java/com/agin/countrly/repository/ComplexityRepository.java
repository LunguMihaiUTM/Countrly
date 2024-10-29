package com.agin.countrly.repository;
import com.agin.countrly.entity.Complexity;
import com.agin.countrly.enums.ComplexityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplexityRepository extends JpaRepository<Complexity, Long> {
    List<Complexity> findByComplexity(ComplexityEnum complexity);
}
