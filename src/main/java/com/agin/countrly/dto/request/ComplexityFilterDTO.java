package com.agin.countrly.dto.request;

import com.agin.countrly.enums.ComplexityEnum;
import lombok.Data;

@Data
public class ComplexityFilterDTO {
    private ComplexityEnum complexityName;
}
