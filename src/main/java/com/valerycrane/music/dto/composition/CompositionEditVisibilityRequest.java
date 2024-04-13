package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompositionEditVisibilityRequest {
    private int id;
    private String visibility;

    public CompositionEditVisibilityRequest(int id, String visibility) {
        this.id = id;
        this.visibility = visibility;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
