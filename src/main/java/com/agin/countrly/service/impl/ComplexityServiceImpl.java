package com.agin.countrly.service.impl;

import com.agin.countrly.dto.request.ComplexityFilterDTO;
import com.agin.countrly.dto.response.ComplexityDTO;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.entity.Complexity;
import com.agin.countrly.entity.Country;
import com.agin.countrly.enums.ComplexityEnum;
import com.agin.countrly.repository.ComplexityRepository;
import com.agin.countrly.repository.CountryRepository;
import com.agin.countrly.service.ComplexityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComplexityServiceImpl implements ComplexityService {
    private final CountryRepository countryRepository;
    private final ComplexityRepository complexityRepository;

    @Override
    public List<CountryDTO> getCountriesByComplexity(ComplexityEnum complexity) {

        if (complexity == null) {
            log.error("Filter cannot be null");
            return List.of(); // sau returnezi o listă goală
        }

        List<Complexity> complexities = complexityRepository.findByComplexity(complexity);

        List<Long> countryIds = complexities.stream()
                .map(Complexity::getCountryId) // extragem countryId din fiecare obiect Complexity
                .toList(); // colectăm într-o listă

        List<Country> countries = countryRepository.findAllById(countryIds);

        return countries.stream()
                        .map(c -> CountryDTO.builder()
                                .id(c.getId())
                                .name(c.getName())
                                .build()
                        ).toList();
    }
}
