package com.valerycrane.music.dto.sample;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public final class SampleMiniatureResponse {
    private int id;
    private String name;
    private Integer beats;

    public SampleMiniatureResponse(int id, String name, Integer beats) {
        this.id = id;
        this.name = name;
        this.beats = beats;
    }

    public SampleMiniatureResponse(int id, String name) {
        this.id = id;
        this.name = name;
        this.beats = null;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("beats")
    public Integer getBeats() {
        return beats;
    }

    @JsonProperty("beats")
    public void setBeats(Integer beats) {
        this.beats = beats;
    }
}
