package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Composition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionRepository extends JpaRepository<Composition, Integer> {
    @Transactional
    List<Composition> findAllByNameContainingIgnoreCaseAndVisibility(String name, String visibility);
}
