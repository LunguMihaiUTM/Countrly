package com.agin.countrly.controller;

import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RankDTO> getRankByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getRankByUserId(userId));
    }
}
