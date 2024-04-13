package com.valerycrane.music.controller;

import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.SuccessResponse;
import com.valerycrane.music.dto.composition.*;
import com.valerycrane.music.dto.user.UserAuthRequest;
import com.valerycrane.music.service.CompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public final class CompositionController {

    private final CompositionService compositionService;

    public CompositionController(@Autowired CompositionService compositionService) {
        this.compositionService = compositionService;
    }

    @GetMapping("/compositions")
    CompositionsResponse getCompositions(@RequestHeader("Auth") String authToken) {
        return compositionService.getUserCompositions(authToken);
    }

    @GetMapping("/composition")
    CompositionResponse getComposition(
            @RequestHeader("Auth") String authToken,
            @RequestParam("id") int id
    ) {
        return compositionService.getCompositionById(id, authToken);
    }

    @PostMapping("/composition")
    CompositionResponse createComposition(
            @RequestHeader("Auth") String authToken,
            @RequestBody CompositionCreateRequest compositionCreateRequest
    ) {
        return compositionService.createComposition(
                compositionCreateRequest.getName(),
                authToken
        );
    }

    @PatchMapping("/composition/blueprint")
    SuccessResponse editCompositionBlueprint(
            @RequestHeader("Auth") String authToken,
            @RequestBody CompositionEditBlueprintRequest compositionEditBlueprintRequest
    ) {
        compositionService.updateCompositionBlueprint(
                compositionEditBlueprintRequest.getId(),
                compositionEditBlueprintRequest.getBlueprint(),
                authToken
        );

        return new SuccessResponse();
    }

    @GetMapping("/composition/history")
    CompositionHistoryResponse getCompositionHistory(
            @RequestHeader("Auth") String authToken,
            @RequestParam("id") int id
    ) {
        return compositionService.getCompositionHistory(id, authToken);
    }

    @PatchMapping("/composition/visibility")
    SuccessResponse editCompositionVisibility(
            @RequestHeader("Auth") String authToken,
            @RequestBody CompositionEditVisibilityRequest compositionEditVisibilityRequest
    ) {
        compositionService.updateCompositionVisibility(
                compositionEditVisibilityRequest.getId(),
                compositionEditVisibilityRequest.getVisibility(),
                authToken
        );

        return new SuccessResponse();
    }

    @PostMapping("/composition/editor")
    SuccessResponse addCompositionEditor(
            @RequestHeader("Auth") String authToken,
            @RequestBody CompositionEditEditorRequest compositionEditEditorRequest
    ) {
        compositionService.addCompositionEditor(
                compositionEditEditorRequest.getId(),
                compositionEditEditorRequest.getEditorId(),
                authToken
        );

        return new SuccessResponse();
    }

    @DeleteMapping("/composition/editor")
    SuccessResponse removeCompositionEditor(
            @RequestHeader("Auth") String authToken,
            @RequestBody CompositionEditEditorRequest compositionEditEditorRequest
    ) {
        compositionService.removeCompositionEditor(
                compositionEditEditorRequest.getId(),
                compositionEditEditorRequest.getEditorId(),
                authToken
        );

        return new SuccessResponse();
    }

    @DeleteMapping("/composition")
    SuccessResponse deleteComposition(
            @RequestHeader("Auth") String authToken,
            @RequestBody CompositionDeleteRequest compositionDeleteRequest
    ) {
        compositionService.deleteComposition(compositionDeleteRequest.getId(), authToken);
        return new SuccessResponse();
    }
}
