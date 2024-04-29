package com.valerycrane.music.service;

import com.valerycrane.music.dto.composition.chat.CompositionChatLoadResponse;
import com.valerycrane.music.dto.composition.chat.CompositionChatResponse;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Optional;

public interface MessageService {
    DeferredResult<CompositionChatResponse> subscribeOnMessages(int compositionId, String authToken);
    DeferredResult<CompositionChatResponse> subscribeOnMessages(int compositionId, int lastMessageId, String authToken);
    CompositionChatLoadResponse getMessages(int compositionId, String authToken);
    CompositionChatLoadResponse getMessages(int compositionId, int firstMessageId, String authToken);
    void sendMessage(String message, int compositionId, String authToken);
}
