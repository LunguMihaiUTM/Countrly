package com.agin.countrly.service;
import com.agin.countrly.dto.response.RankDTO;
import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.entity.User;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId);
    List<UserDTO> getALlUsersWithoutPassword();
}
