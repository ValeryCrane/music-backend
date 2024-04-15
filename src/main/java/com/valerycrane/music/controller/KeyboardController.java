package com.valerycrane.music.controller;

import com.valerycrane.music.dto.keyboard.KeyboardCreateRequest;
import com.valerycrane.music.dto.keyboard.KeyboardCreateResponse;
import com.valerycrane.music.dto.keyboard.KeyboardResponse;
import com.valerycrane.music.dto.keyboard.KeyboardsResponse;
import com.valerycrane.music.service.KeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KeyboardController {

    private final KeyboardService keyboardService;

    public KeyboardController(@Autowired KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @GetMapping("/keyboard")
    public KeyboardResponse getKeyboard(@RequestParam("id") int id) {
        return keyboardService.getKeyboardById(id);
    }

    @GetMapping("/keyboards")
    public KeyboardsResponse getKeyboards() {
        return keyboardService.getAllKeyboards();
    }

    @PostMapping("/keyboard")
    public KeyboardCreateResponse createKeyboard(@RequestBody KeyboardCreateRequest request) {
        return new KeyboardCreateResponse(
                keyboardService.createKeyboard(request.getName(), request.getKeySampleIds())
        );
    }
}
