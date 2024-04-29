package com.valerycrane.music.dto.composition.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompositionChatResponse {
    private List<CompositionMessageResponse> messages;

    public CompositionChatResponse(List<CompositionMessageResponse> messages) {
        this.messages = messages;
    }

    @JsonProperty("messages")
    public List<CompositionMessageResponse> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<CompositionMessageResponse> messages) {
        this.messages = messages;
    }
}
