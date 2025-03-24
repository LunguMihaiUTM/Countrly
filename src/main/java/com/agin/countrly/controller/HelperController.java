package com.agin.countrly.controller;

import com.agin.countrly.service.impl.DataBasePopulationImpl;
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

    @GetMapping("/populate-shapes-and-countries")
    public ResponseEntity<String> populateShapesAndCountries() {
        return ResponseEntity.ok(dataBasePopulation.populateDataBase());
    }

}
