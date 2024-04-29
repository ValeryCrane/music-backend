package com.valerycrane.music.dto.composition.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompositionChatLoadResponse {
    private List<CompositionMessageResponse> messages;
    private boolean isLastBatch;

    public CompositionChatLoadResponse(List<CompositionMessageResponse> messages, boolean isLastBatch) {
        this.messages = messages;
        this.isLastBatch = isLastBatch;
    }

    @JsonProperty("messages")
    public List<CompositionMessageResponse> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<CompositionMessageResponse> messages) {
        this.messages = messages;
    }

    @JsonProperty("is_last_batch")
    public boolean isLastBatch() {
        return isLastBatch;
    }

    @JsonProperty("is_last_batch")
    public void setLastBatch(boolean lastBatch) {
        isLastBatch = lastBatch;
    }
}
