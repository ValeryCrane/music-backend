package com.valerycrane.music.service;

import com.valerycrane.music.dto.*;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.Token;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.TokenRepository;
import com.valerycrane.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Value("${avatars-path}")
    private String avatarPath;

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final ApplicationHostService applicationHostService;

    public UserServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired TokenRepository tokenRepository,
            @Autowired ApplicationHostService applicationHostService
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.applicationHostService = applicationHostService;
    }

    @Override
    public String createAuthTokenForUserWithUsernameAndPassword(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndHashedPassword(
                username,
                hashPassword(password)
        );

        if (user.isPresent()) {
            Token token = new Token();
            token.setValue(UUID.randomUUID().toString());
            token.setUser(user.get());
            tokenRepository.save(token);
            return token.getValue();
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

    @Override
    public String createUserAndProvideAuthTokenWithInfo(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setHashedPassword(hashPassword(password));
        userRepository.save(user);

        Token token = new Token();
        token.setValue(UUID.randomUUID().toString());
        token.setUser(user);
        tokenRepository.save(token);
        return token.getValue();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("Invalid user id");
        }
    }

    @Override
    public CurrentUserResponse getUserInfoByAuthToken(String authToken) {
        User user = getUserByAuthToken(authToken);
        return new CurrentUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCompositions().size(),
                applicationHostService.getHostWithHTTPSchemeAndPath(
                        "/user/avatar?id=" + user.getId()
                )
        );
    }

    @Override
    public UserResponse getUserInfoById(Integer id, String authToken) {
        User currentUser = getUserByAuthToken(authToken);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserResponse(
                    user.get().getId(),
                    user.get().getUsername(),
                    user.get().getCompositions().size(),
                    applicationHostService.getHostWithHTTPSchemeAndPath(
                            "/user/avatar?id=" + user.get().getId()
                    ),
                    currentUser.getFavouriteUsers().contains(user.get())
            );
        } else {
            throw new IllegalArgumentException("Invalid user id");
        }
    }

    @Override
    public void updateUserAvatar(byte[] avatar, String authToken) {
        User user = getUserByAuthToken(authToken);
        try {
            Path path = Paths.get(avatarPath + "/" + user.getId() + ".jpg");
            Files.createDirectories(path.getParent());
            Files.write(path, avatar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserInfo(UserEditRequest userEditRequest, String authToken) {
        User user = getUserByAuthToken(authToken);
        user.setUsername(userEditRequest.username());
        user.setEmail(userEditRequest.email());
        user.setHashedPassword(hashPassword(userEditRequest.password()));
        userRepository.save(user);
    }

    @Override
    public byte[] getUserAvatarById(Integer id) {
        try {
            Path path = Paths.get(avatarPath + "/" + id + ".jpg");
            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            } else {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                InputStream is = classloader.getResourceAsStream("static/default-avatar.jpg");
                if (is != null) {
                    byte[] avatar = is.readAllBytes();
                    is.close();
                    return avatar;
                } else {
                    throw new RuntimeException();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserByAuthToken(String authToken) {
        User user = getUserByAuthToken(authToken);
        userRepository.delete(user);
    }

    @Override
    public CompositionsResponse getUserCompositionsByAuthToken(String authToken) {
        User user = getUserByAuthToken(authToken);
        List<Composition> compositions = user.getCompositions();
        compositions.addAll(user.getEditedCompositions());
        List<Composition> favouriteCompositions = user.getFavouriteCompositions();
        return new CompositionsResponse(
                compositions.size(),
                compositions.stream().map(composition -> new CompositionMiniatureResponse(
                        composition.getId(),
                        composition.getName(),
                        isCompositionFavourite(favouriteCompositions, composition)
                )).toList()
        );
    }

    public CompositionsResponse getUserCompositionsById(Integer id, String authToken) {
        User currentUser = getUserByAuthToken(authToken);
        List<Composition> favouriteCompositions = currentUser.getFavouriteCompositions();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            List<Composition> compositions = user.get().getCompositions();
            return new CompositionsResponse(
                    compositions.size(),
                    compositions.stream().map(composition -> new CompositionMiniatureResponse(
                            composition.getId(),
                            composition.getName(),
                            isCompositionFavourite(favouriteCompositions, composition)
                    )).toList()
            );
        } else {
            throw new IllegalArgumentException("Invalid user id");
        }
    }

    private boolean isCompositionFavourite(List<Composition> favouriteCompositions, Composition composition) {
        for (Composition favouriteComposition: favouriteCompositions) {
            if (favouriteComposition.getId() == composition.getId()) {
                return true;
            }
        }
        return false;
    }

    private Optional<User> getUserByAuthToken(Optional<String> authToken) {
        return authToken.map(this::getUserByAuthToken);
    }

    private User getUserByAuthToken(String authToken) {
        Optional<Token> token = tokenRepository.findFirstByValue(authToken);
        if (token.isPresent()) {
            return token.get().getUser();
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
