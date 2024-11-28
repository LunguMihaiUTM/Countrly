package com.agin.countrly.controller;

import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.entity.User;
import com.agin.countrly.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "https://exciting-wonder-production.up.railway.app")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Get All Users", description = "Consider this API only for test")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsersCustom() {
        return ResponseEntity.ok(userService.getALlUsersWithoutPassword());
    }

}
