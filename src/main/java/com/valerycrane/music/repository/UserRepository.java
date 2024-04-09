package com.valerycrane.music.repository;

import com.valerycrane.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUsernameContainsIgnoreCase(String query);
    User findByUsernameAndHashedPassword(String username, String hashedPassword);
}
