package com.valerycrane.music.service;

import com.valerycrane.music.dto.sample.SampleMiniatureResponse;
import com.valerycrane.music.dto.sample.SamplesResponse;
import com.valerycrane.music.entity.Sample;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.SampleRepository;
import com.valerycrane.music.repository.TokenRepository;
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
    private final TokenRepository tokenRepository;
    private final CommonService commonService;

    public SampleServiceImpl(
            @Autowired SampleRepository sampleRepository,
            @Autowired TokenRepository tokenRepository,
            @Autowired CommonService commonService
    ) {
        this.sampleRepository = sampleRepository;
        this.tokenRepository = tokenRepository;
        this.commonService = commonService;
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
    public SampleMiniatureResponse getSampleInfoById(Integer id) {
        Optional<Sample> optionalSample = sampleRepository.findById(id);
        if (optionalSample.isPresent()) {
            return new SampleMiniatureResponse(
                    optionalSample.get().getId(),
                    optionalSample.get().getName(),
                    optionalSample.get().getBeats()
            );
        } else {
            throw new RuntimeException("Sample not found: " + id);
        }
    }

    @Override
    public int createSample(String name, Integer beats, byte[] data) {
        Sample sample = new Sample(name, beats);
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
        User user = commonService.getUserByAuthToken(authToken);
        List<Sample> samples = user.getSamples();
        return new SamplesResponse(
                samples.size(),
                samples.stream().map(sample -> new SampleMiniatureResponse(
                        sample.getId(),
                        sample.getName(),
                        sample.getBeats()
                )).toList()
        );
    }
}
