package com.valerycrane.music.dto.melody;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MelodyCreateRequest {
    private String name;
    private int keyboardId;
    private String blueprint;

    @JsonCreator
    public MelodyCreateRequest(
            @JsonProperty("name") String name,
            @JsonProperty("keyboard_id") int keyboardId,
            @JsonProperty("blueprint") String blueprint
    ) {
        this.name = name;
        this.keyboardId = keyboardId;
        this.blueprint = blueprint;
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

    @JsonProperty("blueprint")
    public String getBlueprint() {
        return blueprint;
    }

    @JsonProperty("blueprint")
    public void setBlueprint(String blueprint) {
        this.blueprint = blueprint;
    }
}
