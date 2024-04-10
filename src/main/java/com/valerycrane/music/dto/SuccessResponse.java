package com.valerycrane.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SuccessResponse {
    @JsonProperty("success")
    final boolean success = true;

    public SuccessResponse() {
    }
}
