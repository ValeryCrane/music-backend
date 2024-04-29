package com.valerycrane.music.dto.keyboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class KeyboardMiniatureResponse {
    private int id;
    private String name;
    private int numberOfKeys;

    @JsonCreator
    public KeyboardMiniatureResponse(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("number_of_keys") int numberOfKeys
    ) {
        this.id = id;
        this.name = name;
        this.numberOfKeys = numberOfKeys;
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

    @JsonProperty("number_of_keys")
    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    @JsonProperty("number_of_keys")
    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }
}
