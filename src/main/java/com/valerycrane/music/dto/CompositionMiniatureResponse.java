package com.valerycrane.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class CompositionMiniatureResponse {
    private Integer id;
    private String name;
    private Boolean isFavourite;

    public CompositionMiniatureResponse(Integer id, String name, Boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.isFavourite = isFavourite;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("is_favourite")
    public Boolean getFavourite() {
        return isFavourite;
    }

    @JsonProperty("is_favourite")
    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }
}
