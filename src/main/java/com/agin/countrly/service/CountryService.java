package com.agin.countrly.service;
import com.agin.countrly.dto.response.CountryDTO;
import java.util.List;

public interface CountryService {
    List<CountryDTO> getAllCountries();
}
