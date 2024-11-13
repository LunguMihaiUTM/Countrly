package com.agin.countrly.controller;
import com.agin.countrly.dto.response.CountryDTO;
import com.agin.countrly.enums.ComplexityEnum;
import com.agin.countrly.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    @Operation(summary = "Get All Countries", description = "Return a List of CountriesDTO")
    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @Operation(summary = "Get Countries by the complexity",
            description = "The Complexity its an Enum. The option available : 0 - Easy, 1 - Medium, 2 - Hard " +
                    "<br> Return a List of CountryDTO")
    @PostMapping("/complexity/{filter}")
    public ResponseEntity<List<CountryDTO>> getCountriesByComplexity (@PathVariable ComplexityEnum filter){
        return ResponseEntity.ok(countryService.getCountriesByComplexity(filter));
    }


}
