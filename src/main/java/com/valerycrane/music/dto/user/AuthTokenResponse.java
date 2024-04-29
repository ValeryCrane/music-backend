package com.valerycrane.music.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AuthTokenResponse {
    private int userId;
    private String authToken;

    @JsonCreator
    public AuthTokenResponse(
            @JsonProperty("user_id") int userId,
            @JsonProperty("authToken") String authToken
    ) {
        this.userId = userId;
        this.authToken = authToken;
    }

    @JsonProperty("user_id")
    public int getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonProperty("auth_token")
    public String getAuthToken() {
        return authToken;
    }

    @JsonProperty("auth_token")
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
