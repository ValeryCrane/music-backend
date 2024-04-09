package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Melody;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MelodyRepository extends JpaRepository<Melody, Integer> {
}
