package com.agin.countrly.repository;

import com.agin.countrly.dto.response.UserDTO;
import com.agin.countrly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    // password will create an error
    @Query("SELECT new com.agin.countrly.entity.User(u.id, u.username) FROM users u ")
    List<User> findAllUsersWithoutPassword();

    // password will create an error
    @Query("SELECT new com.agin.countrly.entity.User(u.id, u.username) FROM users u WHERE u.id = :id")
    Optional<User> findUserWithoutPasswordById(@Param("id") Long id);
}
