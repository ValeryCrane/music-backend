package com.valerycrane.music.dto.composition.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valerycrane.music.dto.favourite.UserMiniatureResponse;
import com.valerycrane.music.dto.user.UserResponse;

public final class CompositionMessageResponse {
    private int id;
    private String text;
    private UserMiniatureResponse user;
    private boolean isOwn;
    private int unixTime;

    public CompositionMessageResponse(int id, String text, UserMiniatureResponse user, boolean isOwn, int unixTime) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.isOwn = isOwn;
        this.unixTime = unixTime;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("user")
    public UserMiniatureResponse getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(UserMiniatureResponse user) {
        this.user = user;
    }

    @JsonProperty("is_own")
    public boolean isOwn() {
        return isOwn;
    }

    @JsonProperty("is_own")
    public void setOwn(boolean own) {
        isOwn = own;
    }

    @JsonProperty("unix_time")
    public int getUnixTime() {
        return unixTime;
    }

    @JsonProperty("unix_time")
    public void setUnixTime(int unixTime) {
        this.unixTime = unixTime;
    }
}
