package com.agin.countrly.service;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.enums.ComplexityEnum;

import java.util.List;

public interface CountryService {
    List<CountryDTO> getAllCountries();
    List<CountryDTO> getCountriesByComplexity(ComplexityEnum complexityEnum) ;

}
