package com.valerycrane.music.service;

import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.favourite.UsersResponse;

public interface FavouriteService {
    UsersResponse getFavouriteUsers(String authToken);
    CompositionsResponse getFavouriteCompositions(String authToken);
    void setUserIsFavourite(String authToken, int userId, boolean favourite);
    void setCompositionIsFavourite(String authToken, int compositionId, boolean favourite);
}
