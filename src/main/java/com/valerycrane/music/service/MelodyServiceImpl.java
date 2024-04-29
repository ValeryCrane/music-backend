package com.valerycrane.music.service;

import com.valerycrane.music.dto.melody.MelodiesResponse;
import com.valerycrane.music.dto.melody.MelodyMiniatureResponse;
import com.valerycrane.music.dto.melody.MelodyResponse;
import com.valerycrane.music.entity.Keyboard;
import com.valerycrane.music.entity.Melody;
import com.valerycrane.music.repository.KeyboardRepository;
import com.valerycrane.music.repository.MelodyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

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

    @PostConstruct
    public void preloadMelodies() throws IOException {
        System.out.println("Preloading melodies...");
        File indexFile = new File("./melody-index.txt");
        Set<String> indexedFiles = new HashSet<>();
        if (indexFile.exists()) {
            Scanner scanner = new Scanner(indexFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                indexedFiles.add(line);
            }
            scanner.close();
        } else {
            indexFile.createNewFile();
        }

        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("static/melodies");
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(resourceStream)));
        String resource;

        while ((resource = br.readLine()) != null) {
            if (!indexedFiles.contains(resource)) {
                indexedFiles.add(resource);
                preloadMelodyWithName(resource);
            }
        }

        br.close();

        FileWriter writer = new FileWriter(indexFile);
        for (String indexedFile : indexedFiles) {
            writer.write(indexedFile);
            writer.write('\n');
        }
        writer.close();
    }

    private void preloadMelodyWithName(String name) throws IOException {
        InputStream inputStream = this.getClass()
                .getClassLoader().getResourceAsStream("static/melodies/" + name);
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String keyboardName = br.readLine();
        String melodyBlueprint = br.readLine();
        br.close();
        if (keyboardName != null && melodyBlueprint != null) {
            Optional<Keyboard> keyboard = keyboardRepository.findByName(keyboardName);
            if (keyboard.isPresent()) {
                createMelody(name, keyboard.get().getId(), melodyBlueprint);
            }
        }
    }
}
