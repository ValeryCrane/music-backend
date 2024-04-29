package com.valerycrane.music.service;

import com.valerycrane.music.dto.sample.SampleMiniatureResponse;
import com.valerycrane.music.dto.sample.SamplesResponse;

public interface SampleService {
    byte[] getSampleById(Integer id);
    SampleMiniatureResponse getSampleInfoById(Integer id);
    int createSample(String name, Integer beats, byte[] data);
    SamplesResponse getUserSamples(String authToken);
}
