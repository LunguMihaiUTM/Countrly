package com.agin.countrly.service.impl;

import com.agin.countrly.dto.response.CountryCoordinates;
import com.agin.countrly.dto.response.CountryInfoResponse;
import com.agin.countrly.entity.CountryData;
import com.agin.countrly.exception.CountryException;
import com.agin.countrly.repository.CountryDataRepository;
import com.agin.countrly.service.CountryService;
import com.agin.countrly.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.substring;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    @Autowired
    private RestTemplate restTemplate;
    private final CountryDataRepository countryDataRepository;

    @Override
    public CountryCoordinates getCountryCoordinates(String countryName) {
        //
        String encodedCountryName = URLEncoder.encode(countryName, StandardCharsets.UTF_8);
        System.out.println("Encoded country name: " + encodedCountryName);
        // Build the url for the request
        String url = UriComponentsBuilder
                .fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("country", encodedCountryName)
                .queryParam("format", "json")
                .queryParam("limit", "1")
                .queryParam("accept-language", "en")
                .toUriString();

        // Java automatic maps the response from the API to the DTO class
        CountryCoordinates[] response = restTemplate.getForObject(url, CountryCoordinates[].class);

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


    //@Override
    //Old method that use another API - slow
    public CountryInfoResponse _getInfoBetweenCountries(String country1, String country2) {
        CountryCoordinates coordinates1 = getCountryCoordinates(country1);
        CountryCoordinates coordinates2 = getCountryCoordinates(country2);
        if(coordinates1 == null || coordinates2 == null) {
            throw new CountryException("No country found with name: " + country1);
        }

        double distance = calculateDistanceBetweenCountries(coordinates1, coordinates2);
        String direction = getDirectionBetweenCountries(coordinates1, coordinates2);

        return new CountryInfoResponse(distance, direction);

    }

    private List<String> getCountries(){
        String[] countryCodes = Locale.getISOCountries();
        List<String> countries = new ArrayList<>();

        for (String countryCode : countryCodes) {

            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry(obj));
        }

        return countries;
    }

    @Override
    public void saveAllCountries(){
        List<String> countries = getCountries();

        for (String countryName : countries) {

            try{
                CountryCoordinates countryCoordinates = getCountryCoordinates(countryName);

                CountryData countryData = new CountryData();
                countryData.setName(countryName);
                countryData.setLatitude(countryCoordinates.getLat());
                countryData.setLongitude(countryCoordinates.getLon());
                countryDataRepository.save(countryData);

                System.out.println("Saved with success country : " + countryName);
            } catch (Exception e){
                System.err.println("Error for country: " + countryName + " - " + e.getMessage());
            }

        }
    }

    //New method that use info from database - faster
    @Override
    public CountryInfoResponse getInfoBetweenCountries(String country1, String country2) {
        // Adapt the input to the data from DB
        country1 = capitalizeWords(country1);
        country2 = capitalizeWords(country2);

        CountryData countryData1 = countryDataRepository.findByName(country1);
        CountryData countryData2 = countryDataRepository.findByName(country2);

        if(countryData1 == null) {
            throw new CountryException("No country found with name: " + country1);
        }
        if(countryData2 == null) {
            throw new CountryException("No country found with name: " + country2);
        }

        CountryCoordinates coordinates1 = CountryCoordinates.builder()
                .countryName(countryData1.getName())
                .lat(countryData1.getLatitude())
                .lon(countryData1.getLongitude())
                .build();

        CountryCoordinates coordinates2 = CountryCoordinates.builder()
                .countryName(countryData2.getName())
                .lat(countryData2.getLatitude())
                .lon(countryData2.getLongitude())
                .build();

        double distance = calculateDistanceBetweenCountries(coordinates1, coordinates2);
        String direction = getDirectionBetweenCountries(coordinates1, coordinates2);

        return new CountryInfoResponse(distance, direction);

    }

    private String capitalizeWords(String input) {

        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty() && !word.equals("&")) {
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return result.toString().trim();
    }

}
