package com.valerycrane.music.service;

import com.valerycrane.music.dto.keyboard.KeyboardResponse;
import com.valerycrane.music.dto.keyboard.KeyboardsResponse;

import java.util.List;

public interface KeyboardService {
    KeyboardsResponse getAllKeyboards();
    KeyboardResponse getKeyboardById(int id);
    int createKeyboard(String name, List<Integer> keySampleIds);
}
