package com.valerycrane.music.service;

import com.valerycrane.music.dto.sample.SampleMiniatureResponse;
import com.valerycrane.music.dto.sample.SamplesResponse;
import com.valerycrane.music.entity.Sample;
import com.valerycrane.music.entity.Token;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.SampleRepository;
import com.valerycrane.music.repository.TokenRepository;
import com.valerycrane.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public final class SampleServiceImpl implements SampleService {
    @Value("${samples-path}")
    private String samplesPath;

    private final SampleRepository sampleRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public SampleServiceImpl(
            @Autowired SampleRepository sampleRepository,
            @Autowired UserRepository userRepository,
            @Autowired TokenRepository tokenRepository
    ) {
        this.sampleRepository = sampleRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public byte[] getSampleById(Integer id) {
        try {
            Path path = Paths.get(samplesPath + "/" + id + ".wav");
            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            } else {
                throw new RuntimeException("Invalid sample id: " + id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int createSample(String name, byte[] data) {
        Sample sample = new Sample(name);
        int savedSampleId = sampleRepository.save(sample).getId();
        try {
            Path path = Paths.get(samplesPath + "/" + savedSampleId + ".wav");
            Files.createDirectories(path.getParent());
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedSampleId;
    }

    @Override
    public SamplesResponse getUserSamples(String authToken) {
        User user = getUserByAuthToken(authToken);
        List<Sample> samples = user.getSamples();
        return new SamplesResponse(
                samples.size(),
                samples.stream().map(sample -> new SampleMiniatureResponse(
                        sample.getId(),
                        sample.getName()
                )).toList()
        );
    }

    private User getUserByAuthToken(String authToken) {
        Optional<Token> token = tokenRepository.findFirstByValue(authToken);
        if (token.isPresent()) {
            return token.get().getUser();
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }
}
