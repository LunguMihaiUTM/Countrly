package com.agin.countrly.service.impl;

import com.agin.countrly.entity.Country;
import com.agin.countrly.entity.CountryData;
import com.agin.countrly.entity.Shape;
import com.agin.countrly.repository.CountryDataRepository;
import com.agin.countrly.repository.CountryRepository;
import com.agin.countrly.repository.ShapeRepository;
import com.agin.countrly.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataBasePopulationImpl{

    private final CountryRepository countryRepository;
    private final ShapeRepository shapeRepository;
    private final CountryDataRepository countryDataRepository;

    // populate with countries and shapes the DataBase
    public String populateDataBase() {
        List<CountryData> countries = countryDataRepository.findAll();

        String folderPath = "src/main/resources/shapes";
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            return "File not found or does not exist";
        }

        File[] files = folder.listFiles();

        try {
            for (CountryData countryData : countries) {
                boolean found = false;

                if (files != null) {
                    for (File file : files) {
                        if (file.getName().equalsIgnoreCase(countryData.getName() + ".png")) {

                            Country country = new Country();
                            country.setName(countryData.getName());
                            countryRepository.save(country);

                            Shape shape = new Shape();
                            shape.setCountryName(country.getName());
                            shape.setImage(file.getName());
                            shape.setCountryId(country.getId());
                            shapeRepository.save(shape);

                            log.info("Success for {}", countryData.getName());
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    log.warn("Error for {}", countryData.getName());
                }
            }
        } catch (Exception e) {
            log.error("Error occurs", e);
            return "Error occurs";
        }

        return "Successfully populated countries in database";
    }

}
