package com.agin.countrly.controller;

import com.agin.countrly.dto.response.CountryInfoResponse;
import com.agin.countrly.service.LocationService;
import com.agin.countrly.service.impl.DataBasePopulationImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/helper")
public class HelperController {

    private final DataBasePopulationImpl dataBasePopulation;
    private final LocationService locationService;

    // Populate countries and shapes tables
    // Take the name of a country from the countries-data tables,
    // so make sure countries data table is not empty
    // Use after the saveCountryCoordinatesData API
    @GetMapping("/populate-shapes-and-countries")
    @Operation(summary = "Populate countries and shapes tables",
            description = "Populate the countries and shapes tables, before use make sure the countries-data table is not empty")
    public ResponseEntity<String> populateShapesAndCountries() {
        return ResponseEntity.ok(dataBasePopulation.populateDataBase());
    }

    // Use to save all countries coordinates, before user make sure the CountryData table is empty
    @Operation(summary = "Populate countries-data table",
            description = "Populate the countries-data table, with the help of other api")
    @GetMapping("/save-countries")
    public ResponseEntity<Void> saveCountryCoordinatesData(){
        locationService.saveAllCountries();
        return ResponseEntity.ok().build();
    }

}
