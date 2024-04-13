package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class BlueprintMiniatureResponse {
    private int id;
    private Integer parentId;
    private int creatorId;
    private String value;

    public BlueprintMiniatureResponse(int id, int creatorId, Integer parentId, String value) {
        this.id = id;
        this.creatorId = creatorId;
        this.parentId = parentId;
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

    @JsonProperty("creator_id")
    public int getCreatorId() {
        return creatorId;
    }

    @JsonProperty("creator_id")
    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
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
