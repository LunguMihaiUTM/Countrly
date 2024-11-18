package com.agin.countrly.service.impl;

import com.agin.countrly.dto.request.LoginRequest;
import com.agin.countrly.dto.request.RegisterRequest;
import com.agin.countrly.dto.response.AuthenticationResponse;
import com.agin.countrly.entity.User;
import com.agin.countrly.enums.Role;
import com.agin.countrly.exception.AuthException;
import com.agin.countrly.repository.UserRepository;
import com.agin.countrly.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        try{
            var user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .username(user.getUsername())
                    .build();
        } catch (Exception e) {
            throw new AuthException("Invalid username or password");
        }
    }
}