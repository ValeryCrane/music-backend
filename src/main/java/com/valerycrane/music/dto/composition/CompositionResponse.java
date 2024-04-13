package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valerycrane.music.dto.favourite.UserMiniatureResponse;

import java.util.List;

public final class CompositionResponse {
    private int id;
    private String name;
    private boolean isFavourite;
    private String visibility;
    private UserMiniatureResponse creator;
    private List<UserMiniatureResponse> editors;
    private BlueprintResponse blueprint;

    public CompositionResponse(
            int id,
            String name,
            boolean isFavourite,
            String visibility,
            UserMiniatureResponse creator,
            List<UserMiniatureResponse> editors,
            BlueprintResponse blueprint
    ) {
        this.id = id;
        this.name = name;
        this.isFavourite = isFavourite;
        this.visibility = visibility;
        this.creator = creator;
        this.editors = editors;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("is_favourite")
    public boolean isFavourite() {
        return isFavourite;
    }

    @JsonProperty("is_favourite")
    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @JsonProperty("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("creator")
    public UserMiniatureResponse getCreator() {
        return creator;
    }

    @JsonProperty("creator")
    public void setCreator(UserMiniatureResponse creator) {
        this.creator = creator;
    }

    @JsonProperty("editors")
    public List<UserMiniatureResponse> getEditors() {
        return editors;
    }

    @JsonProperty("editors")
    public void setEditors(List<UserMiniatureResponse> editors) {
        this.editors = editors;
    }

    @JsonProperty("blueprint")
    public BlueprintResponse getBlueprint() {
        return blueprint;
    }

    @JsonProperty("blueprint")
    public void setBlueprint(BlueprintResponse blueprint) {
        this.blueprint = blueprint;
    }
}
