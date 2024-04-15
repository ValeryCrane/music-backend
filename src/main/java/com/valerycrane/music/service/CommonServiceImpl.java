package com.valerycrane.music.service;

import com.valerycrane.music.dto.composition.BlueprintResponse;
import com.valerycrane.music.dto.composition.CompositionResponse;
import com.valerycrane.music.dto.favourite.UserMiniatureResponse;
import com.valerycrane.music.entity.Blueprint;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.Token;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class CommonServiceImpl implements CommonService {

    private final TokenRepository tokenRepository;
    private final ApplicationHostService applicationHostService;

    public CommonServiceImpl(
            @Autowired TokenRepository tokenRepository,
            @Autowired ApplicationHostService applicationHostService
    ) {
        this.tokenRepository = tokenRepository;
        this.applicationHostService = applicationHostService;
    }

    @Override
    public User getUserByAuthToken(String authToken) {
        Optional<Token> token = tokenRepository.findFirstByValue(authToken);
        if (token.isPresent()) {
            return token.get().getUser();
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    @Override
    public CompositionResponse createCompositionResponse(Composition composition, User currentUser) {
        User creator = composition.getCreator();
        List<User> editors = composition.getEditors();
        Blueprint blueprint = composition.getBlueprint();
        return new CompositionResponse(
                composition.getId(),
                composition.getName(),
                isFavourite(composition, currentUser),
                composition.getVisibility(),
                createUserMiniature(creator, isFavourite(creator, currentUser)),
                editors.stream().map(
                        editor -> createUserMiniature(editor, isFavourite(editor, currentUser))
                ).toList(),
                new BlueprintResponse(
                        blueprint.getId(),
                        blueprint.getParent() == null ? null : blueprint.getParent().getId(),
                        createUserMiniature(
                                blueprint.getCreator(),
                                isFavourite(blueprint.getCreator(), currentUser)
                        ),
                        blueprint.getValue()
                )
        );
    }

    @Override
    public UserMiniatureResponse createUserMiniature(User user, Boolean isFavourite) {
        return new UserMiniatureResponse(
                user.getId(),
                user.getUsername(),
                user.getCompositions().size(),
                applicationHostService.getHostWithHTTPSchemeAndPath(
                        "/user/avatar?id=" + user.getId()
                ),
                isFavourite
        );
    }

    @Override
    public boolean isFavourite(User user, User currentUser) {
        List<User> favouriteUsers = currentUser.getFavouriteUsers();
        for (User favouriteUser : favouriteUsers) {
            if (favouriteUser.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFavourite(Composition composition, User currentUser) {
        List<Composition> favouriteCompositions = currentUser.getFavouriteCompositions();
        for (Composition favouriteComposition : favouriteCompositions) {
            if (favouriteComposition.getId() == composition.getId()) {
                return true;
            }
        }
        return false;
    }

}
