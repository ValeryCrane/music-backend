package com.valerycrane.music.dto.keyboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class KeyboardCreateRequest {
    private String name;
    private List<Integer> keySampleIds;

    @JsonCreator
    public KeyboardCreateRequest(
            @JsonProperty("name") String name,
            @JsonProperty("key_sample_ids") List<Integer> keySampleIds
    ) {
        this.name = name;
        this.keySampleIds = keySampleIds;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("key_sample_ids")
    public List<Integer> getKeySampleIds() {
        return keySampleIds;
    }

    @JsonProperty("key_sample_ids")
    public void setKeySampleIds(List<Integer> keySampleIds) {
        this.keySampleIds = keySampleIds;
    }
}
