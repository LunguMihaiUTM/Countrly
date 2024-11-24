package com.agin.countrly.service;

import com.agin.countrly.dto.response.CountryCoordinates;
import com.agin.countrly.dto.response.CountryInfoResponse;
import com.agin.countrly.entity.Country;

public interface LocationService {
    CountryCoordinates getCountryCoordinates(String countryName);
    CountryInfoResponse getInfoBetweenCountries(String country1, String country2);
}
