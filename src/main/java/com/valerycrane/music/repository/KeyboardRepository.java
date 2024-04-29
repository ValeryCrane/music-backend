package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Keyboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeyboardRepository extends JpaRepository<Keyboard, Integer> {
    Optional<Keyboard> findByName(String name);
}
