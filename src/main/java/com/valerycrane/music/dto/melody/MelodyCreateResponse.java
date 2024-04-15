package com.valerycrane.music.dto.melody;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MelodyCreateResponse {
    private int id;

    @JsonCreator
    public MelodyCreateResponse(@JsonProperty("id") int id) {
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
