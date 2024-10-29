package com.agin.countrly.service.impl;
import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.entity.Rank;
import com.agin.countrly.entity.User;
import com.agin.countrly.repository.RankRepository;
import com.agin.countrly.repository.UserRepository;
import com.agin.countrly.service.UserService;
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
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public RankDTO getRankByUserId(Long userId) {
        Optional<Rank> rank = rankRepository.findByUserId(userId);
        if(rank.isPresent()) {
            User user = userRepository.findById(userId).orElseThrow();
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            // we use the first get() because of the Optional<Rank> we get with the comand get() the object from the Optional<>
            return RankDTO.builder()
                    .id(rank.get().getId())
                    .name(rank.get().getName())
                    .rating(rank.get().getRating())
                    .user(userDTO)
                    .build();
        }
        return null;
    }
}
