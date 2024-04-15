package com.valerycrane.music.dto.melody;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class MelodiesResponse {
    private int melodyCount;
    private List<MelodyMiniatureResponse> melodies;

    @JsonCreator
    public MelodiesResponse(
            @JsonProperty("melody_count") int melodyCount,
            @JsonProperty("melodies") List<MelodyMiniatureResponse> melodies
    ) {
        this.melodyCount = melodyCount;
        this.melodies = melodies;
    }

    @JsonProperty("melody_count")
    public int getMelodyCount() {
        return melodyCount;
    }

    @JsonProperty("melody_count")
    public void setMelodyCount(int melodyCount) {
        this.melodyCount = melodyCount;
    }

    @JsonProperty("melodies")
    public List<MelodyMiniatureResponse> getMelodies() {
        return melodies;
    }

    @JsonProperty("melodies")
    public void setMelodies(List<MelodyMiniatureResponse> melodies) {
        this.melodies = melodies;
    }
}
