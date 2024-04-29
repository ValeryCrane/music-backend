package com.valerycrane.music.controller;

import com.valerycrane.music.dto.melody.MelodiesResponse;
import com.valerycrane.music.dto.melody.MelodyCreateRequest;
import com.valerycrane.music.dto.melody.MelodyCreateResponse;
import com.valerycrane.music.dto.melody.MelodyResponse;
import com.valerycrane.music.service.MelodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MelodyController {

    private final MelodyService melodyService;

    public MelodyController(@Autowired MelodyService melodyService) {
        this.melodyService = melodyService;
    }

    @GetMapping("/melodies")
    public MelodiesResponse getMelodies() {
        return melodyService.getAllMelodies();
    }

    @GetMapping("/melody")
    public MelodyResponse getMelody(@RequestParam("id") int id) {
        return melodyService.getMelodyById(id);
    }

    @PostMapping("/melody")
    public MelodyCreateResponse createMelody(@RequestBody MelodyCreateRequest request) {
        return new MelodyCreateResponse(melodyService.createMelody(
                request.getName(),
                request.getKeyboardId(),
                request.getBlueprint()
        ));
    }
}
