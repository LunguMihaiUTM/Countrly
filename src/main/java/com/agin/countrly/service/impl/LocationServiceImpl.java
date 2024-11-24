package com.agin.countrly.service.impl;

import com.agin.countrly.dto.response.CountryCoordinates;
import com.agin.countrly.dto.response.CountryInfoResponse;
import com.agin.countrly.exception.CountryException;
import com.agin.countrly.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CountryCoordinates getCountryCoordinates(String countryName) {
        // Create the Url for the API request
        String url = UriComponentsBuilder
                .fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("country", countryName)
                .queryParam("format", "json")
                .queryParam("limit", "1")
                .queryParam("accept-language", "en")
                .toUriString();

        // make the request and automatic map the response to the object by fields name
        CountryCoordinates[] response = restTemplate.getForObject(url, CountryCoordinates[].class);

        // Verify the response
        if (response != null && response.length > 0) {
            return response[0];
        } else {
            throw new CountryException("No country found with name: " + countryName);
        }
    }

    public double calculateDistanceBetweenCountries(CountryCoordinates coordinates1, CountryCoordinates coordinates2) {
        // get the coordinates
        double lat1 = coordinates1.getLat();
        double lon1 = coordinates1.getLon();
        double lat2 = coordinates2.getLat();
        double lon2 = coordinates2.getLon();

        return calculateDistance(lat1, lon1, lat2, lon2);
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Haversine equation
        // R - Earth Radius in km
        final double R = 6378;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public String getDirectionBetweenCountries(CountryCoordinates coordinates1, CountryCoordinates coordinates2) {
        double deltaLat = Math.toRadians(coordinates2.getLat() - coordinates1.getLat());
        double deltaLon = Math.toRadians(coordinates2.getLon() - coordinates1.getLon());

        double angle = Math.atan2(deltaLon, deltaLat);

        double angleDeg = Math.toDegrees(angle);

        // make sure the angle is between 0 and 360
        if (angleDeg < 0) {
            angleDeg += 360;
        }

        return getDirectionFromAngle(angleDeg);
    }

    private String getDirectionFromAngle(double angle) {
        if ((angle >= 337.5 && angle < 360) || (angle >= 0 && angle < 22.5)) {
            return "N";  // Nord
        } else if (angle >= 22.5 && angle < 67.5) {
            return "NE"; // Nord-Est
        } else if (angle >= 67.5 && angle < 112.5) {
            return "E";  // Est
        } else if (angle >= 112.5 && angle < 157.5) {
            return "SE"; // Sud-Est
        } else if (angle >= 157.5 && angle < 202.5) {
            return "S";  // Sud
        } else if (angle >= 202.5 && angle < 247.5) {
            return "SW"; // Sud-Vest
        } else if (angle >= 247.5 && angle < 292.5) {
            return "W";  // Vest
        } else if (angle >= 292.5 && angle < 337.5) {
            return "NW"; // Nord-Vest
        }
        return "Unknown"; // Dacă nu se încadrează în niciuna din direcțiile de mai sus
    }

    @Override
    public CountryInfoResponse getInfoBetweenCountries(String country1, String country2) {
        CountryCoordinates coordinates1 = getCountryCoordinates(country1);
        CountryCoordinates coordinates2 = getCountryCoordinates(country2);
        if(coordinates1 == null || coordinates2 == null) {
            throw new CountryException("No country found with name: " + country1);
        }

        double distance = calculateDistanceBetweenCountries(coordinates1, coordinates2);
        String direction = getDirectionBetweenCountries(coordinates1, coordinates2);

        return new CountryInfoResponse(distance, direction);

    }
}
