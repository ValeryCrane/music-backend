package com.valerycrane.music.dto.composition.chat;

import com.valerycrane.music.entity.User;
import org.springframework.web.context.request.async.DeferredResult;

public class CompositionChatSubscription {
    private final int userId;
    private final DeferredResult<CompositionChatResponse> deferredResult;

    public CompositionChatSubscription(int userId, DeferredResult<CompositionChatResponse> deferredResult) {
        this.userId = userId;
        this.deferredResult = deferredResult;
    }

    public int getUserId() {
        return userId;
    }

    public DeferredResult<CompositionChatResponse> getDeferredResult() {
        return deferredResult;
    }
}
