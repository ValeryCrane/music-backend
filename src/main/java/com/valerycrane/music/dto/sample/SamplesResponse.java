package com.valerycrane.music.dto.sample;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class SamplesResponse {
    private Integer sampleCount;
    private List<SampleMiniatureResponse> samples;

    public SamplesResponse(Integer sampleCount, List<SampleMiniatureResponse> samples) {
        this.sampleCount = sampleCount;
        this.samples = samples;
    }

    @JsonProperty("sample_count")
    public Integer getSampleCount() {
        return sampleCount;
    }

    @JsonProperty("sample_count")
    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    @JsonProperty("samples")
    public List<SampleMiniatureResponse> getSamples() {
        return samples;
    }

    @JsonProperty("samples")
    public void setSamples(List<SampleMiniatureResponse> samples) {
        this.samples = samples;
    }
}
