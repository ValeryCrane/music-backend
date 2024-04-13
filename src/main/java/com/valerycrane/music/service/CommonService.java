package com.valerycrane.music.service;

import com.valerycrane.music.dto.favourite.UserMiniatureResponse;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.User;

public interface CommonService {
    User getUserByAuthToken(String authToken);
    UserMiniatureResponse createUserMiniature(User user, Boolean isFavourite);
    boolean isFavourite(User user, User currentUser);
    boolean isFavourite(Composition composition, User currentUser);
}
