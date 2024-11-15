package com.agin.countrly.service.impl;
import com.agin.countrly.dto.response.ComplexityDTO;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.dto.response.ShapeDTO;
import com.agin.countrly.entity.Complexity;
import com.agin.countrly.entity.Country;
import com.agin.countrly.entity.Shape;
import com.agin.countrly.enums.ComplexityEnum;
import com.agin.countrly.repository.ComplexityRepository;
import com.agin.countrly.repository.CountryRepository;
import com.agin.countrly.repository.ShapeRepository;
import com.agin.countrly.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ShapeRepository shapeRepository;
    private final ComplexityRepository complexityRepository;
    @Value("${shape.resources.path}")
    private String shapeResourcePath;

    @Override
    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> {
                    Shape shape = shapeRepository.findByCountryId(country.getId())
                            .orElse(null);
                    Complexity complexity = complexityRepository.findByCountryId(country.getId())
                            .orElse(null);
                    return mapToCountryDTO(country, shape, complexity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CountryDTO> getCountriesByComplexity(ComplexityEnum complexityEnum) {
        if (complexityEnum == null) {
            log.error("Filter cannot be null");
            return List.of();
        }

        List<Complexity> complexities = complexityRepository.findByComplexity(complexityEnum);
        //find all the countries Ids for specific Enum
        List<Long> countryIds = complexities.stream()
                .map(Complexity::getCountryId)
                .toList();

        List<Country> countries = countryRepository.findAllById(countryIds);

        return countries.stream()
                .map(country -> {
                    Shape shape = shapeRepository.findByCountryId(country.getId())
                            .orElse(null);
                    Complexity complexity = complexityRepository.findByCountryId(country.getId())
                            .orElse(null);
                    return mapToCountryDTO(country, shape, complexity);
                })
                .collect(Collectors.toList());
    }

    private CountryDTO mapToCountryDTO(Country country, Shape shape, Complexity complexity) {
        ShapeDTO shapeDTO = null;
        ComplexityDTO complexityDTO = null;
        // map the shape
        if (shape != null) {
            try {
                byte[] imageData = Files.readAllBytes(Path.of(shapeResourcePath, shape.getImage()));
                shapeDTO = ShapeDTO.builder()
                        .id(shape.getId())
                        .image(imageData)
                        .build();
            } catch (Exception e) {
                log.error("Error reading image file for country {}: {}", country.getName(), e.getMessage());
            }
        }
        // map the complexity
        if(complexity != null) {
            complexityDTO = ComplexityDTO.builder()
                    .id(complexity.getId())
                    .complexity(complexity.getComplexity())
                    .build();
        }

        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .shape(shapeDTO)
                .complexity(complexityDTO)
                .build();
    }
}
