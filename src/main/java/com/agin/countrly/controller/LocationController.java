package com.agin.countrly.controller;

import com.agin.countrly.dto.response.CountryCoordinates;
import com.agin.countrly.dto.response.CountryInfoResponse;
import com.agin.countrly.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/get-info-between-countries")
    public ResponseEntity<CountryInfoResponse> getInfoBetweenCountries(
            @RequestParam String userInputCountry,
            @RequestParam String targetCountry) {
        return ResponseEntity.ok(locationService.getInfoBetweenCountries(userInputCountry, targetCountry));
    }
}
