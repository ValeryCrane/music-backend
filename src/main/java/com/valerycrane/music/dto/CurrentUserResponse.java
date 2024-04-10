package com.valerycrane.music.dto;

public record CurrentUserResponse(Integer id, String username, String email, Integer compositionCount, String avatarURL) {
}
