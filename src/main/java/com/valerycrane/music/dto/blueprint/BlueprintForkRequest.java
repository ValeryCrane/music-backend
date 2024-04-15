package com.valerycrane.music.dto.blueprint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class BlueprintForkRequest {
    private int id;
    private String compositionName;

    @JsonCreator
    public BlueprintForkRequest(
            @JsonProperty("id") int id,
            @JsonProperty("composition_name") String compositionName
    ) {
        this.id = id;
        this.compositionName = compositionName;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("composition_name")
    public String getCompositionName() {
        return compositionName;
    }

    @JsonProperty("composition_name")
    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }
}
