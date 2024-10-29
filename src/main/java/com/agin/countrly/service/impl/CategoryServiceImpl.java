package com.agin.countrly.service.impl;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.entity.Country;
import com.agin.countrly.repository.CountryRepository;
import com.agin.countrly.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();

        return countries.stream()
                .map(country -> CountryDTO.builder()
                        .id(country.getId())
                        .name(country.getName())
                        .build()
                ).collect(Collectors.toList());
    }
}
