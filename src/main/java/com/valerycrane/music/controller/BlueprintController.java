package com.valerycrane.music.controller;

import com.valerycrane.music.dto.blueprint.BlueprintForkRequest;
import com.valerycrane.music.dto.composition.BlueprintResponse;
import com.valerycrane.music.dto.composition.CompositionResponse;
import com.valerycrane.music.service.BlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public final class BlueprintController {

    private final BlueprintService blueprintService;

    public BlueprintController(@Autowired BlueprintService blueprintService) {
        this.blueprintService = blueprintService;
    }

    @GetMapping("/blueprint")
    public BlueprintResponse getBlueprint(@RequestHeader("Auth") String authToken, @RequestParam int id) {
        return blueprintService.getBlueprintById(id, authToken);
    }

    @PostMapping("/blueprint/fork")
    public CompositionResponse forkBlueprint(
            @RequestHeader("Auth") String authToken,
            @RequestBody BlueprintForkRequest blueprintForkRequest
    ) {
        return blueprintService.forkBlueprint(
                blueprintForkRequest.getId(),
                blueprintForkRequest.getCompositionName(),
                authToken
        );
    }
}
