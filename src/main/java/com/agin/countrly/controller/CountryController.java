package com.agin.countrly.controller;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.enums.ComplexityEnum;
import com.agin.countrly.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @PostMapping("/complexity/{filter}")
    public ResponseEntity<List<CountryDTO>> getCountriesByComplexity (@PathVariable ComplexityEnum filter){
        return ResponseEntity.ok(countryService.getCountriesByComplexity(filter));
    }


}
