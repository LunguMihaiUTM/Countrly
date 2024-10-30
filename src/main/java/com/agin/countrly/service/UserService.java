package com.agin.countrly.service;
import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.dto.response.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    RankDTO getRankByUserId(Long userId);
}
