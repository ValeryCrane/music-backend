package com.valerycrane.music.service;

import com.valerycrane.music.dto.CompositionMiniatureResponse;
import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.favourite.UsersResponse;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.CompositionRepository;
import com.valerycrane.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class FavouriteServiceImpl implements FavouriteService {

    private final UserRepository userRepository;
    private final CompositionRepository compositionRepository;
    private final CommonService commonService;

    public FavouriteServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired CompositionRepository compositionRepository,
            @Autowired CommonService commonService
    ) {
        this.userRepository = userRepository;
        this.compositionRepository = compositionRepository;
        this.commonService = commonService;
    }

    @Override
    public UsersResponse getFavouriteUsers(String authToken) {
        User currentUser = commonService.getUserByAuthToken(authToken);
        List<User> favouriteUsers = currentUser.getFavouriteUsers();
        return new UsersResponse(
                favouriteUsers.size(),
                favouriteUsers.stream().map(
                        user -> commonService.createUserMiniature(user, true)
                ).toList()
        );
    }

    @Override
    public CompositionsResponse getFavouriteCompositions(String authToken) {
        User currentUser = commonService.getUserByAuthToken(authToken);
        List<Composition> favouriteCompositions = currentUser.getFavouriteCompositions();
        return new CompositionsResponse(
                favouriteCompositions.size(),
                favouriteCompositions.stream().map(composition -> new CompositionMiniatureResponse(
                        composition.getId(),
                        composition.getName(),
                        true
                )).toList()
        );
    }

    @Override
    public void setUserIsFavourite(String authToken, int userId, boolean favourite) {
        User currentUser = commonService.getUserByAuthToken(authToken);
        Optional<User> favouriteUser = userRepository.findById(userId);
        if (favouriteUser.isPresent()) {
            if (favourite) {
                currentUser.getFavouriteUsers().add(favouriteUser.get());
            } else {
                currentUser.getFavouriteUsers().removeIf(user -> user.getId() == favouriteUser.get().getId());
            }
            userRepository.save(currentUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void setCompositionIsFavourite(String authToken, int compositionId, boolean favourite) {
        User currentUser = commonService.getUserByAuthToken(authToken);
        Optional<Composition> favouriteComposition = compositionRepository.findById(compositionId);
        if (favouriteComposition.isPresent()) {
            if (favourite) {
                currentUser.getFavouriteCompositions().add(favouriteComposition.get());
            } else {
                currentUser.getFavouriteCompositions().removeIf(
                        composition -> composition.getId() == favouriteComposition.get().getId()
                );
            }
            userRepository.save(currentUser);
        } else {
            throw new RuntimeException("Composition not found");
        }
    }
}
