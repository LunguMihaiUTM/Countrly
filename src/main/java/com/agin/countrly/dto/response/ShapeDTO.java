package com.agin.countrly.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShapeDTO {
    private Long id;
    private String countryName;
    private byte[] image;
    private CountryDTO country;
}