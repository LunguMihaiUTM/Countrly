package com.agin.countrly.service.impl;
import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.entity.Rank;
import com.agin.countrly.entity.User;
import com.agin.countrly.repository.RankRepository;
import com.agin.countrly.repository.UserRepository;
import com.agin.countrly.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RankRepository rankRepository;

    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .rank(getUserById(user.getId()).getRank())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            Optional<Rank> rank = rankRepository.findByUserId(userId);
            RankDTO rankDTO = RankDTO.builder()
                    .id(rank.get().getId())
                    .rating(rank.get().getRating())
                    .name(rank.get().getName())
                    .build();

            return UserDTO.builder()
                    .id(user.get().getId())
                    .username(user.get().getUsername())
                    .rank(rankDTO)
                    .build();
        }
        throw new EntityNotFoundException("User not found: " + userId);
    }


}
