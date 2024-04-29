package com.valerycrane.music.service;

import com.valerycrane.music.dto.composition.chat.CompositionChatLoadResponse;
import com.valerycrane.music.dto.composition.chat.CompositionChatResponse;
import com.valerycrane.music.dto.composition.chat.CompositionChatSubscription;
import com.valerycrane.music.dto.composition.chat.CompositionMessageResponse;
import com.valerycrane.music.dto.user.UserResponse;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.Message;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.CompositionRepository;
import com.valerycrane.music.repository.MessageRepository;
import com.valerycrane.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {
    @Value("${chat.page-size}")
    private int chatPageSize;

    private final MessageRepository messageRepository;
    private final CompositionRepository compositionRepository;
    private final UserRepository userRepository;
    private final CommonService commonService;

    private final Map<Integer, List<CompositionChatSubscription>> subscriptions = new HashMap<>();

    public MessageServiceImpl(
            @Autowired MessageRepository messageRepository,
            @Autowired CompositionRepository compositionRepository,
            @Autowired UserRepository userRepository,
            @Autowired CommonService commonService
    ) {
        this.messageRepository = messageRepository;
        this.compositionRepository = compositionRepository;
        this.userRepository = userRepository;
        this.commonService = commonService;
    }

    @Override
    public DeferredResult<CompositionChatResponse> subscribeOnMessages(int compositionId, String authToken) {
        DeferredResult<CompositionChatResponse> deferredResult = new DeferredResult<>();
        User user = commonService.getUserByAuthToken(authToken);
        CompositionChatSubscription subscription = new CompositionChatSubscription(user.getId(), deferredResult);
        Composition composition = getCompositionById(compositionId);

        List<Message> messages = composition.getMessages();
        messages.sort(Comparator.comparing(Message::getTimeSent).reversed());

        if (!messages.isEmpty()) {
            deferredResult.setResult(new CompositionChatResponse(messagesResponseFromMessages(messages, user)));
        } else {
            addSubscriptionToDictionary(subscription, compositionId);
        }

        return deferredResult;
    }

    @Override
    public DeferredResult<CompositionChatResponse> subscribeOnMessages(int compositionId, int lastMessageId, String authToken) {
        DeferredResult<CompositionChatResponse> deferredResult = new DeferredResult<>();
        User user = commonService.getUserByAuthToken(authToken);
        CompositionChatSubscription subscription = new CompositionChatSubscription(user.getId(), deferredResult);
        Composition composition = getCompositionById(compositionId);

        List<Message> messages = composition.getMessages();
        messages.sort(Comparator.comparing(Message::getTimeSent).reversed());

        int lastMessageIndex = findMessageIndex(lastMessageId, messages);

        if (lastMessageIndex == 0) {
            addSubscriptionToDictionary(subscription, compositionId);
        } else {
            deferredResult.setResult(new CompositionChatResponse(
                    messagesResponseFromMessages(
                            messages.subList(0, lastMessageIndex), user
                    )
            ));
        }

        return deferredResult;
    }

    @Override
    public CompositionChatLoadResponse getMessages(int compositionId, int firstMessageId, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Composition composition = getCompositionById(compositionId);

        List<Message> messages = composition.getMessages();
        messages.sort(Comparator.comparing(Message::getTimeSent).reversed());

        int firstMessageIndex = findMessageIndex(firstMessageId, messages);

        if (messages.size() - firstMessageIndex - 1 > chatPageSize) {
            return new CompositionChatLoadResponse(
                    messagesResponseFromMessages(messages.subList(firstMessageIndex + 1, firstMessageIndex + chatPageSize + 1), user),
                    false
            );
        } else {
            return new CompositionChatLoadResponse(
                    messagesResponseFromMessages(messages.subList(firstMessageIndex + 1, messages.size()), user),
                    true
            );
        }
    }

    @Override
    public CompositionChatLoadResponse getMessages(int compositionId, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Composition composition = getCompositionById(compositionId);

        List<Message> messages = composition.getMessages();
        messages.sort(Comparator.comparing(Message::getTimeSent).reversed());

        if (messages.size() > chatPageSize) {
            return new CompositionChatLoadResponse(
                    messagesResponseFromMessages(messages.subList(0, chatPageSize), user),
                    false
            );
        } else {
            return new CompositionChatLoadResponse(
                    messagesResponseFromMessages(messages, user),
                    true
            );
        }
    }

    @Override
    public void sendMessage(String text, int compositionId, String authToken) {
        User currentUser = commonService.getUserByAuthToken(authToken);
        Composition composition = getCompositionById(compositionId);

        Message message = new Message(currentUser, composition, text);
        composition.getMessages().add(message);
        currentUser.getMessages().add(message);
        Message savedMessage = messageRepository.save(message);

        notifyAllSubscribers(savedMessage, compositionId);
    }

    private void addSubscriptionToDictionary(CompositionChatSubscription subscription, Integer compositionId) {
        subscriptions.putIfAbsent(compositionId, new ArrayList<>());
        subscriptions.get(compositionId).add(subscription);
    }

    private void notifyAllSubscribers(Message message, int compositionId) {
        if (subscriptions.containsKey(compositionId)) {
            for (CompositionChatSubscription subscription : subscriptions.get(compositionId)) {
                Optional<User> user = userRepository.findById(subscription.getUserId());
                if (user.isPresent()) {
                    subscription.getDeferredResult().setResult(new CompositionChatResponse(
                            Collections.singletonList(messageResponseFromMessage(message, user.get()))
                    ));
                }
            }
            subscriptions.remove(compositionId);
        }
    }

    private List<CompositionMessageResponse> messagesResponseFromMessages(List<Message> messages, User currentUser) {
        return messages.stream().map(message -> messageResponseFromMessage(message, currentUser)).toList();
    }

    private CompositionMessageResponse messageResponseFromMessage(Message message, User currentUser) {
        return new CompositionMessageResponse(
                message.getId(),
                message.getMessage(),
                commonService.createUserMiniature(
                        message.getUser(),
                        commonService.isFavourite(
                                message.getUser(),
                                currentUser
                        )
                ),
                message.getUser().getId() == currentUser.getId(),
                (int) (message.getTimeSent().getTime() / 1000)
        );
    }

    private Composition getCompositionById(Integer compositionId) {
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        if (composition.isEmpty()) {
            throw new IllegalArgumentException("Invalid composition ID: " + compositionId);
        }

        return composition.get();
    }

    private int findMessageIndex(int messageId, List<Message> messages) {
        Integer messageIndex = null;
        for (int i = 0; i < messages.size(); i++) {
            if(messageId == messages.get(i).getId()) {
                messageIndex = i;
            }
        }

        if (messageIndex == null) {
            throw new IllegalArgumentException("Invalid message ID: " + messageId);
        }

        return messageIndex;
    }
}
