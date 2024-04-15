package com.valerycrane.music.dto.keyboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class KeyboardMiniatureResponse {
    private int id;
    private String name;

    @JsonCreator
    public KeyboardMiniatureResponse(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name
    ) {
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
