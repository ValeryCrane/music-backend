package com.valerycrane.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class CompositionsResponse {
    private Integer compositionCount;
    private List<CompositionMiniatureResponse> compositions;

    public CompositionsResponse(Integer compositionCount, List<CompositionMiniatureResponse> compositions) {
        this.compositionCount = compositionCount;
        this.compositions = compositions;
    }

    @JsonProperty("composition_count")
    public Integer getCompositionCount() {
        return compositionCount;
    }

    @JsonProperty("composition_count")
    public void setCompositionCount(Integer compositionCount) {
        this.compositionCount = compositionCount;
    }

    @JsonProperty("compositions")
    public List<CompositionMiniatureResponse> getCompositions() {
        return compositions;
    }

    @JsonProperty("compositions")
    public void setCompositions(List<CompositionMiniatureResponse> compositions) {
        this.compositions = compositions;
    }
}
