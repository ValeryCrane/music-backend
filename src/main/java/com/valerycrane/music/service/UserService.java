package com.valerycrane.music.service;

import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.CurrentUserResponse;
import com.valerycrane.music.dto.UserEditRequest;
import com.valerycrane.music.dto.UserResponse;
import com.valerycrane.music.entity.User;

import java.util.Optional;

public interface UserService {
    User getUserById(Integer id);
    String createAuthTokenForUserWithUsernameAndPassword(String username, String password);
    String createUserAndProvideAuthTokenWithInfo(String username, String email, String password);
    CurrentUserResponse getUserInfoByAuthToken(String authToken);
    UserResponse getUserInfoById(Integer id, String authToken);
    void updateUserAvatar(byte[] avatar, String authToken);
    void updateUserInfo(UserEditRequest userEditRequest, String authToken);
    byte[] getUserAvatarById(Integer id);
    void deleteUserByAuthToken(String authToken);
    CompositionsResponse getUserCompositionsByAuthToken(String authToken);
    CompositionsResponse getUserCompositionsById(Integer id, String authToken);
}
