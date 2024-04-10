package com.valerycrane.music.dto.sample;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SampleMiniatureResponse {
    private int id;
    private String name;

    public SampleMiniatureResponse(int id, String name) {
        this.id = id;
        this.name = name;
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
}
