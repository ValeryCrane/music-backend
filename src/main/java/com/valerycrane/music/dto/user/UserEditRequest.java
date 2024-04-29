package com.valerycrane.music.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public final class UserEditRequest {
    private Optional<String> username;
    private Optional<String> email;
    private Optional<String> password;

    public UserEditRequest(Optional<String> username, Optional<String> email, Optional<String> password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @JsonProperty("username")
    public Optional<String> getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    @JsonProperty("email")
    public Optional<String> getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    @JsonProperty("password")
    public Optional<String> getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(Optional<String> password) {
        this.password = password;
    }
}
