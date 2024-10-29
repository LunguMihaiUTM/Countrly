package com.agin.countrly.dto.response;

import com.agin.countrly.enums.ComplexityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexityDTO {
    private Long id;
    private ComplexityEnum complexity;
}