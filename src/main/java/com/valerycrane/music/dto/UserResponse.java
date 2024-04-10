package com.valerycrane.music.dto;

public record UserResponse(Integer id, String username, Integer compositionCount, String avatarURL, Boolean isFavorite) {
}
