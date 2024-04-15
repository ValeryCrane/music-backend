package com.valerycrane.music.service;

import com.valerycrane.music.dto.composition.BlueprintResponse;
import com.valerycrane.music.dto.composition.CompositionResponse;
import com.valerycrane.music.entity.Blueprint;

public interface BlueprintService {
    BlueprintResponse getBlueprintById(int id, String authToken);
    CompositionResponse forkBlueprint(int id, String name, String authToken);
}
