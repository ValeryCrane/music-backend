package com.valerycrane.music.dto.search;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.valerycrane.music.dto.favourite.UserMiniatureResponse;

import java.util.List;

public final class SearchUsersResponse {
    private int userCount;
    private int page;
    private int totalPages;
    private List<UserMiniatureResponse> users;

    @JsonCreator
    public SearchUsersResponse(
            @JsonProperty("user_count") int userCount,
            @JsonProperty("page") int page,
            @JsonProperty("total_pages") int totalPages,
            @JsonProperty("users") List<UserMiniatureResponse> users
    ) {
        this.userCount = userCount;
        this.page = page;
        this.totalPages = totalPages;
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

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("total_pages")
    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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
