package com.agin.countrly.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankDTO {
    private Long id;
    private String name;
    private Double rating;
    private UserDTO user;
}