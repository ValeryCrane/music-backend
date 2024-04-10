package com.valerycrane.music.dto.favourite;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class UserMiniatureResponse {
    private int id;
    private String username;
    private int compositionCount;
    private String avatarURL;
    private boolean isFavourite;

    public UserMiniatureResponse(int id, String username, int compositionCount, String avatarURL, boolean isFavourite) {
        this.id = id;
        this.username = username;
        this.compositionCount = compositionCount;
        this.avatarURL = avatarURL;
        this.isFavourite = isFavourite;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("avatar_url")
    public String getAvatarURL() {
        return avatarURL;
    }

    @JsonProperty("avatar_url")
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @JsonProperty("composition_count")
    public int getCompositionCount() {
        return compositionCount;
    }

    @JsonProperty("composition_count")
    public void setCompositionCount(int compositionCount) {
        this.compositionCount = compositionCount;
    }

    @JsonProperty("is_favourite")
    public boolean isFavourite() {
        return isFavourite;
    }

    @JsonProperty("is_favourite")
    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
