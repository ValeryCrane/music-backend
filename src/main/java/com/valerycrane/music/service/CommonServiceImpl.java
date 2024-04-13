package com.valerycrane.music.service;

import com.valerycrane.music.dto.favourite.UserMiniatureResponse;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.Token;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.TokenRepository;
import com.valerycrane.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class CommonServiceImpl implements CommonService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final ApplicationHostService applicationHostService;

    public CommonServiceImpl(
            @Autowired TokenRepository tokenRepository,
            @Autowired UserRepository userRepository,
            @Autowired ApplicationHostService applicationHostService
    ) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
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
