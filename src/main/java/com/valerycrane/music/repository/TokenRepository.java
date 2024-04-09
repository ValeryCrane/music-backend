package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findFirstByValue(String value);
}
