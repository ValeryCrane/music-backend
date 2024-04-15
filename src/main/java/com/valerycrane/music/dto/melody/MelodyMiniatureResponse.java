package com.valerycrane.music.dto.melody;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MelodyMiniatureResponse {
    private int id;
    private String name;
    private int keyboardId;

    @JsonCreator
    public MelodyMiniatureResponse(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("keyboard_id") int keyboardId
    ) {
        this.id = id;
        this.name = name;
        this.keyboardId = keyboardId;
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

    @JsonProperty("keyboard_id")
    public int getKeyboardId() {
        return keyboardId;
    }

    @JsonProperty("keyboard_id")
    public void setKeyboardId(int keyboardId) {
        this.keyboardId = keyboardId;
    }
}
