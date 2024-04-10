package com.valerycrane.music.repository;

import com.valerycrane.music.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    List<User> findAllByUsernameContainsIgnoreCase(String query);
    @Transactional
    Optional<User> findByUsernameAndHashedPassword(String username, String hashedPassword);
}
