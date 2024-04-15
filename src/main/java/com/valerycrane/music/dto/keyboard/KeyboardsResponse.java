package com.valerycrane.music.dto.keyboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class KeyboardsResponse {
    private int keyboardCount;
    private List<KeyboardMiniatureResponse> keyboards;

    @JsonCreator
    public KeyboardsResponse(
            @JsonProperty("keyboard_count") int keyboardCount,
            @JsonProperty("keyboards") List<KeyboardMiniatureResponse> keyboards
    ) {
        this.keyboardCount = keyboardCount;
        this.keyboards = keyboards;
    }

    @JsonProperty("keyboard_count")
    public int getKeyboardCount() {
        return keyboardCount;
    }

    @JsonProperty("keyboard_count")
    public void setKeyboardCount(int keyboardCount) {
        this.keyboardCount = keyboardCount;
    }

    @JsonProperty("keyboards")
    public List<KeyboardMiniatureResponse> getKeyboards() {
        return keyboards;
    }

    @JsonProperty("keyboards")
    public void setKeyboards(List<KeyboardMiniatureResponse> keyboards) {
        this.keyboards = keyboards;
    }
}
