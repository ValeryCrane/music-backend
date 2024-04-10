package com.valerycrane.music.repository;

import com.valerycrane.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUsernameContainsIgnoreCase(String query);
    Optional<User> findByUsernameAndHashedPassword(String username, String hashedPassword);
}
