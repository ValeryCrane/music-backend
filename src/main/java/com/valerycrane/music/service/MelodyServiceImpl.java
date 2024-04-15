package com.valerycrane.music.service;

import com.valerycrane.music.dto.melody.MelodiesResponse;
import com.valerycrane.music.dto.melody.MelodyMiniatureResponse;
import com.valerycrane.music.dto.melody.MelodyResponse;
import com.valerycrane.music.entity.Keyboard;
import com.valerycrane.music.entity.Melody;
import com.valerycrane.music.repository.KeyboardRepository;
import com.valerycrane.music.repository.MelodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class MelodyServiceImpl implements MelodyService {

    private final MelodyRepository melodyRepository;
    private final KeyboardRepository keyboardRepository;

    public MelodyServiceImpl(
            @Autowired MelodyRepository melodyRepository,
            @Autowired KeyboardRepository keyboardRepository
    ) {
        this.melodyRepository = melodyRepository;
        this.keyboardRepository = keyboardRepository;
    }

    @Override
    public MelodiesResponse getAllMelodies() {
        List<Melody> melodies = melodyRepository.findAll();
        return new MelodiesResponse(
                melodies.size(),
                melodies.stream().map(melody -> new MelodyMiniatureResponse(
                        melody.getId(),
                        melody.getName(),
                        melody.getKeyboard().getId()
                )).toList()
        );
    }

    @Override
    public MelodyResponse getMelodyById(int id) {
        Optional<Melody> melody = melodyRepository.findById(id);
        if (melody.isPresent()) {
            return new MelodyResponse(
                    melody.get().getId(),
                    melody.get().getName(),
                    melody.get().getKeyboard().getId(),
                    melody.get().getBlueprint()
            );
        } else {
            throw new RuntimeException("Melody not found");
        }
    }

    @Override
    public int createMelody(String name, int keyboardId, String blueprint) {
        Optional<Keyboard> keyboard = keyboardRepository.findById(keyboardId);
        if (keyboard.isPresent()) {
            Melody melody = new Melody(name, blueprint, keyboard.get());
            return melodyRepository.save(melody).getId();
        } else {
            throw new RuntimeException("Keyboard not found");
        }
    }
}
