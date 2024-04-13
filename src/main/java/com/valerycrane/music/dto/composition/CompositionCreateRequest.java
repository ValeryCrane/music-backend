package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompositionCreateRequest {
    private String name;

    @JsonCreator
    public CompositionCreateRequest(
            @JsonProperty("name") String name
    ) {
        this.name = name;
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
