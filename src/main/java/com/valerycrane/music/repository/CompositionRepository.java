package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Composition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionRepository extends JpaRepository<Composition, Integer> {
    List<Composition> findAllByNameContainingIgnoreCaseAndVisibility(String name, String visibility);
}
