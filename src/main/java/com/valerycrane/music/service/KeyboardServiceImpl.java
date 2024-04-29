package com.valerycrane.music.service;

import com.valerycrane.music.dto.keyboard.KeyboardMiniatureResponse;
import com.valerycrane.music.dto.keyboard.KeyboardResponse;
import com.valerycrane.music.dto.keyboard.KeyboardsResponse;
import com.valerycrane.music.entity.Keyboard;
import com.valerycrane.music.entity.KeyboardSample;
import com.valerycrane.music.entity.Sample;
import com.valerycrane.music.repository.KeyboardRepository;
import com.valerycrane.music.repository.KeyboardSampleRepository;
import com.valerycrane.music.repository.SampleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public final class KeyboardServiceImpl implements KeyboardService {

    private final KeyboardRepository keyboardRepository;
    private final KeyboardSampleRepository keyboardSampleRepository;
    private final SampleRepository sampleRepository;
    private final SampleService sampleService;

    public KeyboardServiceImpl(
            @Autowired KeyboardRepository keyboardRepository,
            @Autowired KeyboardSampleRepository keyboardSampleRepository,
            @Autowired SampleRepository sampleRepository,
            @Autowired SampleService sampleService
    ) {
        this.keyboardRepository = keyboardRepository;
        this.keyboardSampleRepository = keyboardSampleRepository;
        this.sampleRepository = sampleRepository;
        this.sampleService = sampleService;
    }

    @Override
    public KeyboardsResponse getAllKeyboards() {
        List<Keyboard> keyboards = keyboardRepository.findAll();
        return new KeyboardsResponse(
                keyboards.size(),
                keyboards.stream().map(keyboard -> new KeyboardMiniatureResponse(
                        keyboard.getId(),
                        keyboard.getName(),
                        keyboard.getKeyboardSamples().size()
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
        Keyboard keyboard = new Keyboard(name);
        Keyboard savedKeyboard = keyboardRepository.save(keyboard);

        for (int i = 0; i < keySampleIds.size(); i++) {
            Optional<Sample> sample = sampleRepository.findById(keySampleIds.get(i));
            if (sample.isEmpty()) {
                throw new RuntimeException("Could not find sample with id " + keySampleIds.get(i));
            }

            KeyboardSample keyboardSample = new KeyboardSample(i, sample.get(), savedKeyboard);
            savedKeyboard.getKeyboardSamples().add(keyboardSample);
        }

        return keyboardRepository.save(savedKeyboard).getId();
    }

    @PostConstruct
    public void preloadKeyboards() throws IOException {
        System.out.println("Preloading keyboards...");
        File indexFile = new File("./index.txt");
        Set<String> indexedDirectories = new HashSet<>();
        if (indexFile.exists()) {
            Scanner scanner = new Scanner(indexFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                indexedDirectories.add(line);
            }
            scanner.close();
        } else {
            indexFile.createNewFile();
        }

        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("static/keyboards");
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(resourceStream)));
        String resource;

        while ((resource = br.readLine()) != null) {
            if (!indexedDirectories.contains(resource)) {
                indexedDirectories.add(resource);
                loadKeyboardWithName(resource);
            }
        }
        br.close();

        FileWriter writer = new FileWriter(indexFile);
        for (String indexedDirectory : indexedDirectories) {
            writer.write(indexedDirectory);
            writer.write('\n');
        }
        writer.close();
    }

    private void loadKeyboardWithName(String name) throws IOException {
        InputStream inputStream = this.getClass()
                .getClassLoader().getResourceAsStream("static/keyboards/" + name);
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        Map<Integer, Integer> keyboardMap = new HashMap<>();
        String resource;

        while ((resource = br.readLine()) != null) {
            String sampleName = resource.substring(0, resource.lastIndexOf('.'));
            Integer sampleId = loadSampleForKeyboard(name, sampleName);
            Integer sampleIndex = Integer.parseInt(sampleName) - 1;
            keyboardMap.put(sampleIndex, sampleId);
        }
        br.close();

        List<Integer> keySampleIds = Arrays.asList(new Integer[keyboardMap.size()]);
        for (Integer keySampleIndex : keyboardMap.keySet()) {
            keySampleIds.set(keySampleIndex, keyboardMap.get(keySampleIndex));
        }

        createKeyboard(name, keySampleIds);
    }

    private int loadSampleForKeyboard(String keyboardName, String sampleName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("static/keyboards/" + keyboardName + "/" + sampleName + ".wav");
        byte[] sample = Objects.requireNonNull(is).readAllBytes();
        is.close();

        return sampleService.createSample(sampleName, null, sample);
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
