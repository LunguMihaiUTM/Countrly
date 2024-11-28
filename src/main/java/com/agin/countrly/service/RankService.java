package com.agin.countrly.service;

import com.agin.countrly.dto.response.RankDTO;

import java.util.List;
import java.util.Optional;

public interface RankService {
    void incrementRatingByUserId(Long userId, Double increment);
    List<RankDTO> getAllRanks();
}
