package com.valerycrane.music.dto.composition;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompositionEditEditorRequest {
    private int id;
    private int editorId;

    public CompositionEditEditorRequest(int id, int editorId) {
        this.id = id;
        this.editorId = editorId;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("editor_id")
    public int getEditorId() {
        return editorId;
    }

    @JsonProperty("editor_id")
    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }
}
