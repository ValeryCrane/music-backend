package com.valerycrane.music.controller;

import com.valerycrane.music.dto.*;
import com.valerycrane.music.dto.user.*;
import com.valerycrane.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public final class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public AuthTokenResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.createUserAndProvideAuthTokenWithInfo(
                userRegisterRequest.getUsername(),
                userRegisterRequest.getEmail(),
                userRegisterRequest.getPassword()
        );
    }

    @PostMapping("/user/auth")
    public AuthTokenResponse auth(@RequestBody UserAuthRequest userAuthRequest) {
        return userService.createAuthTokenForUserWithUsernameAndPassword(
                userAuthRequest.getUsername(),
                userAuthRequest.getPassword()
        );
    }

    @GetMapping("/user")
    public Object getUser(
            @RequestHeader("Auth") String authToken,
            @RequestParam(name = "id", required = false) Optional<Integer> id
    ) {
        if (id.isPresent()) {
            return userService.getUserInfoById(id.get(), authToken);
        } else {
            return userService.getUserInfoByAuthToken(authToken);
        }
    }

    @PostMapping(
            value = "/user/avatar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse updateUserAvatar(
            @RequestHeader("Auth") String authToken,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            userService.updateUserAvatar(
                    file.getBytes(),
                    authToken
            );
            return new SuccessResponse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/user/avatar", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserAvatar(@RequestParam("id") int id) {
        return userService.getUserAvatarById(id);
    }

    @PatchMapping("/user")
    public SuccessResponse updateUser(
            @RequestHeader("Auth") String authToken,
            @RequestBody UserEditRequest userEditRequest
    ) {
        userService.updateUserInfo(userEditRequest, authToken);
        return new SuccessResponse();
    }

    @DeleteMapping("/user")
    public SuccessResponse deleteUser(@RequestHeader("Auth") String authToken) {
        userService.deleteUserByAuthToken(authToken);
        return new SuccessResponse();
    }

    @GetMapping("/user/compositions")
    public CompositionsResponse getCompositions(
            @RequestHeader("Auth") String authToken,
            @RequestParam(name = "id", required = false) Optional<Integer> id
    ) {
        if (id.isPresent()) {
            return userService.getUserCompositionsById(id.get(), authToken);
        } else {
            return userService.getUserCompositionsByAuthToken(authToken);
        }
    }

}
