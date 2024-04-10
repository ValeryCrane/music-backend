package com.valerycrane.music.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class UserResponse {
    private Integer id;
    private String username;
    private Integer compositionCount;
    private String avatarURL;
    private Boolean isFavorite;

    public UserResponse(Integer id, String username, Integer compositionCount, String avatarURL, Boolean isFavorite) {
        this.id = id;
        this.username = username;
        this.compositionCount = compositionCount;
        this.avatarURL = avatarURL;
        this.isFavorite = isFavorite;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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

    @JsonProperty("composition_count")
    public Integer getCompositionCount() {
        return compositionCount;
    }

    @JsonProperty("composition_count")
    public void setCompositionCount(Integer compositionCount) {
        this.compositionCount = compositionCount;
    }

    @JsonProperty("avatar_url")
    public String getAvatarURL() {
        return avatarURL;
    }

    @JsonProperty("avatar_url")
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @JsonProperty("is_favourite")
    public Boolean getFavorite() {
        return isFavorite;
    }

    @JsonProperty("is_favourite")
    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
