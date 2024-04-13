package com.valerycrane.music.service;

import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.composition.CompositionHistoryResponse;
import com.valerycrane.music.dto.composition.CompositionResponse;

public interface CompositionService {
    CompositionsResponse getUserCompositions(String authToken);
    CompositionResponse getCompositionById(int compositionId, String authToken);
    CompositionResponse createComposition(String name, String authToken);
    void updateCompositionBlueprint(int compositionId, String blueprint, String authToken);
    void updateCompositionVisibility(int compositionId, String visibility, String authToken);
    void addCompositionEditor(int compositionId, int editorId, String authToken);
    void removeCompositionEditor(int compositionId, int editorId, String authToken);
    CompositionHistoryResponse getCompositionHistory(int compositionId, String authToken);
    void deleteComposition(int compositionId, String authToken);
}
