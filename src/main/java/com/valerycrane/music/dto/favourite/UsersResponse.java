package com.valerycrane.music.dto.favourite;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class UsersResponse {
    private int userCount;
    private List<UserMiniatureResponse> users;

    public UsersResponse(int userCount, List<UserMiniatureResponse> users) {
        this.userCount = userCount;
        this.users = users;
    }

    @JsonProperty("user_count")
    public int getUserCount() {
        return userCount;
    }

    @JsonProperty("user_count")
    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    @JsonProperty("users")
    public List<UserMiniatureResponse> getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(List<UserMiniatureResponse> users) {
        this.users = users;
    }
}
