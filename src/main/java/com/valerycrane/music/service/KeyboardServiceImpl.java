package com.valerycrane.music.service;

import com.valerycrane.music.dto.keyboard.KeyboardMiniatureResponse;
import com.valerycrane.music.dto.keyboard.KeyboardResponse;
import com.valerycrane.music.dto.keyboard.KeyboardsResponse;
import com.valerycrane.music.entity.Keyboard;
import com.valerycrane.music.entity.KeyboardSample;
import com.valerycrane.music.entity.Sample;
import com.valerycrane.music.repository.KeyboardRepository;
import com.valerycrane.music.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public final class KeyboardServiceImpl implements KeyboardService {

    private final KeyboardRepository keyboardRepository;
    private final SampleRepository sampleRepository;

    public KeyboardServiceImpl(
            @Autowired KeyboardRepository keyboardRepository,
            @Autowired SampleRepository sampleRepository
    ) {
        this.keyboardRepository = keyboardRepository;
        this.sampleRepository = sampleRepository;
    }

    @Override
    public KeyboardsResponse getAllKeyboards() {
        List<Keyboard> keyboards = keyboardRepository.findAll();
        return new KeyboardsResponse(
                keyboards.size(),
                keyboards.stream().map(keyboard -> new KeyboardMiniatureResponse(
                        keyboard.getId(),
                        keyboard.getName()
                )).toList()
        );
    }

    @Override
    public KeyboardResponse getKeyboardById(int id) {
        Optional<Keyboard> keyboard = keyboardRepository.findById(id);
        if (keyboard.isPresent()) {
            return new KeyboardResponse(
                    keyboard.get().getId(),
                    keyboard.get().getName(),
                    getKeyboardSampleIds(keyboard.get())
            );
        } else {
            throw new RuntimeException("Could not find keyboard with id " + id);
        }
    }

    @Override
    public int createKeyboard(String name, List<Integer> keySampleIds) {
        List<KeyboardSample> keyboardSamples = new ArrayList<>();
        for (int i = 0; i < keySampleIds.size(); i++) {
            Optional<Sample> sample = sampleRepository.findById(keySampleIds.get(i));
            if (sample.isEmpty()) {
                throw new RuntimeException("Could not find sample with id " + keySampleIds.get(i));
            }

            keyboardSamples.add(new KeyboardSample(i, sample.get()));
        }

        Keyboard keyboard = new Keyboard(keyboardSamples, name);
        Keyboard savedKeyboard = keyboardRepository.save(keyboard);
        return savedKeyboard.getId();
    }

    private List<Integer> getKeyboardSampleIds(Keyboard keyboard) {
        List<KeyboardSample> keyboardSamples = keyboard.getKeyboardSamples();
        keyboardSamples.sort(Comparator.comparingInt(KeyboardSample::getKeyIndex));
        List<Integer> keyboardSampleIds = new ArrayList<>(keyboardSamples.size());
        for (int i = 0; i < keyboardSamples.size(); ++i) {
            if (keyboardSamples.get(i).getKeyIndex() != i) {
                throw new RuntimeException("Internal error: wrong keyboard format");
            }
            keyboardSampleIds.add(keyboardSamples.get(i).getSample().getId());
        }

        return keyboardSampleIds;
    }
}
