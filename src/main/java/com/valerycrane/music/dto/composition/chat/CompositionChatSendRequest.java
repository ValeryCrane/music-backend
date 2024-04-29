package com.valerycrane.music.dto.composition.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompositionChatSendRequest {
    private String message;
    private int compositionId;

    public CompositionChatSendRequest(String message, int compositionId) {
        this.message = message;
        this.compositionId = compositionId;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("composition_id")
    public int getCompositionId() {
        return compositionId;
    }

    @JsonProperty("composition_id")
    public void setCompositionId(int compositionId) {
        this.compositionId = compositionId;
    }
}
