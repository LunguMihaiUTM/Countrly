package com.agin.countrly.service;

import com.agin.countrly.dto.request.ComplexityFilterDTO;
import com.agin.countrly.dto.response.ComplexityDTO;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.entity.Complexity;
import com.agin.countrly.enums.ComplexityEnum;

import java.util.List;

public interface ComplexityService {
    List<CountryDTO> getCountriesByComplexity(ComplexityEnum complexity);
}
