package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Transactional
    Optional<Token> findFirstByValue(String value);
    @Transactional
    void deleteAllByUserId(Integer userId);
}
