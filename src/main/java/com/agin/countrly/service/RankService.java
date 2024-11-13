package com.agin.countrly.service;

public interface RankService {
    void incrementRatingByUserId(Long userId, Double increment);
}
