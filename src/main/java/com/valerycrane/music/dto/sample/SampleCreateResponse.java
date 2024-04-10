package com.valerycrane.music.dto.sample;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SampleCreateResponse {
    private int id;

    public SampleCreateResponse(int id) {
        this.id = id;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }
}
