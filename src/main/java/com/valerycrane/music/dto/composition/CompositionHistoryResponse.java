package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class CompositionHistoryResponse {
    private int blueprintCount;
    private List<BlueprintMiniatureResponse> blueprints;

    public CompositionHistoryResponse(int blueprintCount, List<BlueprintMiniatureResponse> blueprints) {
        this.blueprintCount = blueprintCount;
        this.blueprints = blueprints;
    }

    @JsonProperty("blueprint_count")
    public int getBlueprintCount() {
        return blueprintCount;
    }

    @JsonProperty("blueprint_count")
    public void setBlueprintCount(int blueprintCount) {
        this.blueprintCount = blueprintCount;
    }

    @JsonProperty("blueprints")
    public List<BlueprintMiniatureResponse> getBlueprints() {
        return blueprints;
    }

    @JsonProperty("blueprints")
    public void setBlueprints(List<BlueprintMiniatureResponse> blueprints) {
        this.blueprints = blueprints;
    }
}
