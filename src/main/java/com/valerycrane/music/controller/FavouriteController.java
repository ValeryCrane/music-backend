package com.valerycrane.music.controller;

import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.SuccessResponse;
import com.valerycrane.music.dto.favourite.FavouriteRequest;
import com.valerycrane.music.dto.favourite.UsersResponse;
import com.valerycrane.music.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public final class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(@Autowired FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping("/favourite/users")
    public UsersResponse getFavouriteUsers(@RequestHeader("Auth") String authToken) {
        return favouriteService.getFavouriteUsers(authToken);
    }

    @PostMapping("/favourite/user")
    public SuccessResponse addUserToFavourite(
            @RequestHeader("Auth") String authToken,
            @RequestBody FavouriteRequest favouriteRequest
    ) {
        favouriteService.setUserIsFavourite(authToken, favouriteRequest.getId(), true);
        return new SuccessResponse();
    }

    @DeleteMapping("/favourite/user")
    public SuccessResponse removeUserFromFavourite(
            @RequestHeader("Auth") String authToken,
            @RequestBody FavouriteRequest favouriteRequest
    ) {
        favouriteService.setUserIsFavourite(authToken, favouriteRequest.getId(), false);
        return new SuccessResponse();
    }

    @GetMapping("/favourite/compositions")
    public CompositionsResponse getFavouriteCompositions(@RequestHeader("Auth") String authToken) {
        return favouriteService.getFavouriteCompositions(authToken);
    }

    @PostMapping("/favourite/composition")
    public SuccessResponse addCompositionToFavourite(
            @RequestHeader("Auth") String authToken,
            @RequestBody FavouriteRequest favouriteRequest
    ) {
        favouriteService.setCompositionIsFavourite(authToken, favouriteRequest.getId(), true);
        return new SuccessResponse();
    }

    @DeleteMapping("/favourite/composition")
    public SuccessResponse removeCompositionFromFavourite(
            @RequestHeader("Auth") String authToken,
            @RequestBody FavouriteRequest favouriteRequest
    ) {
        favouriteService.setCompositionIsFavourite(authToken, favouriteRequest.getId(), false);
        return new SuccessResponse();
    }
}
