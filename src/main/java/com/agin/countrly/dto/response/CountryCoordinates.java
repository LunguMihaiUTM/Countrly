package com.agin.countrly.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryCoordinates {
    private double lat;
    private double lon;
    //custom name for the country name
    @JsonProperty("display_name")
    private String countryName;
}
