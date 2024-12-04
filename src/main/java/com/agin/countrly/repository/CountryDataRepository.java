package com.agin.countrly.repository;

import com.agin.countrly.entity.CountryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDataRepository extends JpaRepository<CountryData, Long> {
    CountryData findByName(String country);
}
