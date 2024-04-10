package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findFirstByValue(String value);
}
