package com.valerycrane.music.dto;

import java.util.List;

public record CompositionsResponse(Integer compositionCount, List<CompositionMiniatureResponse> compositions) {
}
