package com.valerycrane.music.service;

import com.valerycrane.music.dto.sample.SamplesResponse;

public interface SampleService {
    byte[] getSampleById(Integer id);
    int createSample(String name, byte[] data);
    SamplesResponse getUserSamples(String authToken);
}
