package com.valerycrane.music.controller;

import com.valerycrane.music.dto.SuccessResponse;
import com.valerycrane.music.dto.composition.CompositionEditEditorRequest;
import com.valerycrane.music.dto.composition.chat.CompositionChatLoadResponse;
import com.valerycrane.music.dto.composition.chat.CompositionChatResponse;
import com.valerycrane.music.dto.composition.chat.CompositionChatSendRequest;
import com.valerycrane.music.dto.melody.MelodyResponse;
import com.valerycrane.music.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Optional;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(@Autowired MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/composition/chat")
    public DeferredResult<CompositionChatResponse> subscribeOnMessages(
            @RequestParam("composition_id") int compositionId,
            @RequestParam("last_message_id") Optional<Integer> lastMessageId,
            @RequestHeader("Auth") String authToken
    ) {
        if (lastMessageId.isPresent()) {
            return messageService.subscribeOnMessages(
                    compositionId, lastMessageId.get(), authToken
            );
        } else {
            return messageService.subscribeOnMessages(compositionId, authToken);
        }
    }

    @GetMapping("/composition/chat/load")
    public CompositionChatLoadResponse loadMessages(
            @RequestParam("composition_id") int compositionId,
            @RequestParam("first_message_id") Optional<Integer> firstMessageId,
            @RequestHeader("Auth") String authToken
    ) {
        if (firstMessageId.isPresent()) {
            return messageService.getMessages(compositionId, firstMessageId.get(), authToken);
        } else {
            return messageService.getMessages(compositionId, authToken);
        }
    }

    @PostMapping("/composition/chat/send")
    public SuccessResponse sendMessage(
            @RequestHeader("Auth") String authToken,
            @RequestBody CompositionChatSendRequest compositionChatSendRequest
    ) {
        messageService.sendMessage(
                compositionChatSendRequest.getMessage(), compositionChatSendRequest.getCompositionId(), authToken
        );

        return new SuccessResponse();
    }
}
