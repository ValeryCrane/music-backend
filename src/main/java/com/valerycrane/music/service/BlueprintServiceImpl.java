package com.valerycrane.music.service;

import com.valerycrane.music.dto.composition.BlueprintResponse;
import com.valerycrane.music.dto.composition.CompositionResponse;
import com.valerycrane.music.entity.Blueprint;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.BlueprintRepository;
import com.valerycrane.music.repository.CompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class BlueprintServiceImpl implements BlueprintService {

    private final CompositionRepository compositionRepository;
    private final BlueprintRepository blueprintRepository;
    private final CommonService commonService;

    public BlueprintServiceImpl(
            @Autowired CompositionRepository compositionRepository,
            @Autowired BlueprintRepository blueprintRepository,
            @Autowired CommonService commonService
    ) {
        this.compositionRepository = compositionRepository;
        this.blueprintRepository = blueprintRepository;
        this.commonService = commonService;
    }

    @Override
    public BlueprintResponse getBlueprintById(int id, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Blueprint> blueprint = blueprintRepository.findById(id);
        if (blueprint.isPresent()) {
            return new BlueprintResponse(
                    blueprint.get().getId(),
                    blueprint.get().getParent() == null ? null : blueprint.get().getParent().getId(),
                    commonService.createUserMiniature(
                            blueprint.get().getCreator(),
                            commonService.isFavourite(blueprint.get().getCreator(), user)
                    ),
                    blueprint.get().getValue()
            );
        } else {
            throw new RuntimeException("Could not find blueprint with id " + id);
        }
    }

    @Override
    public CompositionResponse forkBlueprint(int id, String name, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Blueprint> blueprint = blueprintRepository.findById(id);
        if (blueprint.isPresent()) {
            Composition composition = new Composition(name, user, blueprint.get());
            Composition savedComposition = compositionRepository.save(composition);
            return commonService.createCompositionResponse(savedComposition, user);
        } else {
            throw new RuntimeException("Could not find blueprint with id " + id);
        }
    }
}
