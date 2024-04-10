package com.valerycrane.music.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CurrentUserResponse {
    private Integer id;
    private String username;
    private String email;
    private Integer compositionCount;
    private String avatarURL;

    public CurrentUserResponse(Integer id, String username, String email, Integer compositionCount, String avatarURL) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.compositionCount = compositionCount;
        this.avatarURL = avatarURL;
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

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
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
}
