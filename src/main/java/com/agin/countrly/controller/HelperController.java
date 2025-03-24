package com.agin.countrly.controller;

import com.agin.countrly.service.LocationService;
import com.agin.countrly.service.impl.DataBasePopulationImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/helper")
public class HelperController {

    private final DataBasePopulationImpl dataBasePopulation;
    private final LocationService locationService;

    // Populate both shapes and countries tables
    // Before to run this method make sure that table countries_data
    // If countries_data table is empty run the saveCountryInfo method
    @Operation(summary = "Populate shapes and countries tables in DataBase",
            description = "Run to save data in tables : countries and shapes")
    @GetMapping("/populate-shapes-and-countries")
    public ResponseEntity<String> populateShapesAndCountries() {
        return ResponseEntity.ok(dataBasePopulation.populateDataBase());
    }

    //Use to save all countries coordinates, before user make sure the CountryData table is empty
    @Operation(summary = "Save Countries", description = "Run to save data in table countries_data table")
    @GetMapping("/save-countries")
    public ResponseEntity<String> saveCountryInfo(){
        locationService.saveAllCountries();
        return ResponseEntity.ok("Successfully saved countries");
    }

}
