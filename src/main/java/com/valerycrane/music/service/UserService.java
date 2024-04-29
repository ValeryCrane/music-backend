package com.valerycrane.music.service;

import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.user.AuthTokenResponse;
import com.valerycrane.music.dto.user.CurrentUserResponse;
import com.valerycrane.music.dto.user.UserEditRequest;
import com.valerycrane.music.dto.user.UserResponse;
import com.valerycrane.music.entity.User;

public interface UserService {
    AuthTokenResponse createAuthTokenForUserWithUsernameAndPassword(String username, String password);
    AuthTokenResponse createUserAndProvideAuthTokenWithInfo(String username, String email, String password);

    CurrentUserResponse getUserInfoByAuthToken(String authToken);
    UserResponse getUserInfoById(Integer id, String authToken);

    void updateUserAvatar(byte[] avatar, String authToken);
    void updateUserInfo(UserEditRequest userEditRequest, String authToken);
    byte[] getUserAvatarById(Integer id);
    void deleteUserByAuthToken(String authToken);
    CompositionsResponse getUserCompositionsByAuthToken(String authToken);
    CompositionsResponse getUserCompositionsById(Integer id, String authToken);
}
