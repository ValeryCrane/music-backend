package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valerycrane.music.dto.favourite.UserMiniatureResponse;

public final class BlueprintResponse {
    private int id;
    private Integer parentId;
    private UserMiniatureResponse creator;
    private String value;

    public BlueprintResponse(int id, Integer parentId, UserMiniatureResponse creator, String value) {
        this.id = id;
        this.parentId = parentId;
        this.creator = creator;
        this.value = value;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("parent_id")
    public Integer getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("creator")
    public UserMiniatureResponse getCreator() {
        return creator;
    }

    @JsonProperty("creator")
    public void setCreator(UserMiniatureResponse creator) {
        this.creator = creator;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }
}
