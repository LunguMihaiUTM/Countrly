package com.agin.countrly.service.impl;

import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.entity.Rank;
import com.agin.countrly.entity.User;
import com.agin.countrly.repository.RankRepository;
import com.agin.countrly.repository.UserRepository;
import com.agin.countrly.service.RankService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void incrementRatingByUserId(Long userId, Double increment) {
        // Validate the increment
        if (increment == null || increment <= 0) {
            throw new IllegalArgumentException("Increment value must be positive");
        }

        // Validate the User
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Increment
        int updatedRows = rankRepository.incrementRatingByUserId(userId, increment);

        if (updatedRows == 0) {
            throw new EntityNotFoundException("Rank not found for user: " + userId);
        }

        log.info("Successfully incremented rating for user {} by {}", userId, increment);
    }

    @Override
    public List<RankDTO> getAllRanks(){
        List<Rank> ranks = rankRepository.findAll();
        return ranks.stream()
                .map(rank -> RankDTO.builder()
                        .id(rank.getId())
                        .rating(rank.getRating())
                        .user(getUserById(rank.getUserId()))
                        .build())
                .toList();
    }

    private UserDTO getUserById(Long userId) {
       Optional<User> user = userRepository.findUserWithoutPasswordById(userId);
       if(user.isPresent()) {
           return UserDTO.builder()
                   .id(user.get().getId())
                   .username(user.get().getUsername())
                   .build();
       }
        throw new EntityNotFoundException("User not found: " + userId);
    }

}
