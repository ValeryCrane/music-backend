package com.valerycrane.music.dto.keyboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class KeyboardCreateResponse {
    private int id;

    @JsonCreator
    public KeyboardCreateResponse(@JsonProperty("id") int id) {
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
