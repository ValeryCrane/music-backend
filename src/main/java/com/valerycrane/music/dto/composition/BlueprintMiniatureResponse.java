package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valerycrane.music.dto.favourite.UserMiniatureResponse;

public final class BlueprintMiniatureResponse {
    private int id;
    private Integer parentId;
    private UserMiniatureResponse creator;

    public BlueprintMiniatureResponse(int id, Integer parentId, UserMiniatureResponse creator) {
        this.id = id;
        this.parentId = parentId;
        this.creator = creator;
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
}
