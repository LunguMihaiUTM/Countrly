package com.agin.countrly.controller;
import com.agin.countrly.dto.request.ComplexityFilterDTO;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.enums.ComplexityEnum;
import com.agin.countrly.service.ComplexityService;
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
    private final ComplexityService complexityService;

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @PostMapping("/complexity")
    public ResponseEntity<List<CountryDTO>> getCountriesByComplexity(@RequestBody(required = false) ComplexityEnum filter){
        return ResponseEntity.ok(complexityService.getCountriesByComplexity(filter));
    }


}
