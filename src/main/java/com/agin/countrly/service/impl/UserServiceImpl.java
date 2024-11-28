package com.agin.countrly.service.impl;
import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.entity.User;
import com.agin.countrly.repository.UserRepository;
import com.agin.countrly.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getALlUsersWithoutPassword(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .build())
                .toList();
    }

    @Override
    public UserDTO getUserById(Long userId){
        Optional<User> user = userRepository.findUserWithoutPasswordById(userId);
        if(user.isPresent()){
            return UserDTO.builder()
                    .id(user.get().getId())
                    .username(user.get().getUsername())
                    .build();
        }
        throw new EntityNotFoundException("User not found: " + userId);
    }

}
