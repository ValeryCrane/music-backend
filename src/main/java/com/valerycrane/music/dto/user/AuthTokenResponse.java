package com.valerycrane.music.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class AuthTokenResponse {
    private String authToken;

    public AuthTokenResponse(String authToken) {
        this.authToken = authToken;
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
