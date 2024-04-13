package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompositionEditBlueprintRequest {
    private int id;
    private String blueprint;

    public CompositionEditBlueprintRequest(int id, String blueprint) {
        this.id = id;
        this.blueprint = blueprint;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
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
