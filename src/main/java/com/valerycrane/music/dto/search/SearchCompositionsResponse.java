package com.valerycrane.music.dto.search;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.valerycrane.music.dto.CompositionMiniatureResponse;

import java.util.List;

public final class SearchCompositionsResponse {
    private int compositionCount;
    private int page;
    private int totalPages;
    private List<CompositionMiniatureResponse> compositions;

    @JsonCreator
    public SearchCompositionsResponse(
            @JsonProperty("composition_count") int compositionCount,
            @JsonProperty("page") int page,
            @JsonProperty("total_pages") int totalPages,
            @JsonProperty("compositions") List<CompositionMiniatureResponse> compositions
    ) {
        this.compositionCount = compositionCount;
        this.page = page;
        this.totalPages = totalPages;
        this.compositions = compositions;
    }

    @JsonProperty("composition_count")
    public int getCompositionCount() {
        return compositionCount;
    }

    @JsonProperty("composition_count")
    public void setCompositionCount(int compositionCount) {
        this.compositionCount = compositionCount;
    }

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("total_pages")
    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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
