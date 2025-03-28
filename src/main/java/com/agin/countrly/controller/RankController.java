package com.agin.countrly.controller;

import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.service.CountryService;
import com.agin.countrly.service.RankService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rank")
public class RankController {
    private final RankService rankService;

    @Operation(summary = "Increment Rating for User by the Id of User",
            description = "For the increment, the input value should be a positive double number, " +
                    "for userId an User Id for which you want to increment the rank. <br>" +
                    "Please notice that this method doesn't return anything (returns void).")
    @PatchMapping("/users/{userId}/increment")
    public ResponseEntity<String> incrementRating(
            @PathVariable("userId") Long userId,
            @RequestParam Double increment) {
        rankService.incrementRatingByUserId(userId, increment);
        return ResponseEntity.ok("Rank incremented successfully.");
    }
    @Operation(summary = "Get All Ranks", description = "Return all Ranks with the User entity")
    @GetMapping("/all")
    public ResponseEntity<List<RankDTO>> getAllRanks() {
        return ResponseEntity.ok(rankService.getAllRanks());
    }
}
