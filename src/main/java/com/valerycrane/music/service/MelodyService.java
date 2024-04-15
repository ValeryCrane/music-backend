package com.valerycrane.music.service;

import com.valerycrane.music.dto.keyboard.KeyboardResponse;
import com.valerycrane.music.dto.keyboard.KeyboardsResponse;
import com.valerycrane.music.dto.melody.MelodiesResponse;
import com.valerycrane.music.dto.melody.MelodyResponse;

import java.util.List;

public interface MelodyService {
    MelodiesResponse getAllMelodies();
    MelodyResponse getMelodyById(int id);
    int createMelody(String name, int keyboardId, String blueprint);
}
