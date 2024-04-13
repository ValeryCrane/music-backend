package com.valerycrane.music.service;

import com.valerycrane.music.dto.CompositionMiniatureResponse;
import com.valerycrane.music.dto.CompositionsResponse;
import com.valerycrane.music.dto.composition.BlueprintMiniatureResponse;
import com.valerycrane.music.dto.composition.BlueprintResponse;
import com.valerycrane.music.dto.composition.CompositionHistoryResponse;
import com.valerycrane.music.dto.composition.CompositionResponse;
import com.valerycrane.music.entity.Blueprint;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.CompositionRepository;
import com.valerycrane.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class CompositionServiceImpl implements CompositionService {

    private final CompositionRepository compositionRepository;
    private final UserRepository userRepository;
    private final CommonService commonService;

    public CompositionServiceImpl(
            @Autowired CompositionRepository compositionRepository,
            @Autowired UserRepository userRepository,
            @Autowired CommonService commonService
    ) {
        this.compositionRepository = compositionRepository;
        this.userRepository = userRepository;
        this.commonService = commonService;
    }

    @Override
    public CompositionsResponse getUserCompositions(String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        List<Composition> compositions = user.getCompositions();
        compositions.addAll(user.getEditedCompositions());
        return new CompositionsResponse(
                compositions.size(),
                compositions.stream().map(composition -> new CompositionMiniatureResponse(
                        composition.getId(),
                        composition.getName(),
                        commonService.isFavourite(composition, user)
                )).toList()
        );
    }

    @Override
    public CompositionResponse getCompositionById(int compositionId, String authToken) {
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        if (composition.isPresent()) {
            return createCompositionResponse(composition.get(), authToken);
        } else {
            throw new RuntimeException("Could not find composition with id " + compositionId);
        }
    }

    @Override
    public CompositionResponse createComposition(String name, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Composition composition = new Composition(name, user);
        Composition createdComposition = compositionRepository.save(composition);
        return createCompositionResponse(createdComposition, authToken);
    }

    @Override
    public void updateCompositionBlueprint(int compositionId, String blueprint, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        if (composition.isPresent()) {
            if (!isUserAllowedToEdit(user, composition.get())) {
                throw new RuntimeException("User is not allowed to edit composition");
            }
            Blueprint previousBlueprint = composition.get().getBlueprint();
            Blueprint newBlueprint = new Blueprint(blueprint, previousBlueprint, user);
            composition.get().setBlueprint(newBlueprint);
            compositionRepository.save(composition.get());
        } else {
            throw new RuntimeException("Could not find composition with id " + compositionId);
        }
    }

    @Override
    public void updateCompositionVisibility(int compositionId, String visibility, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        if (composition.isPresent()) {
            if (!isUserAllowedToEdit(user, composition.get())) {
                throw new RuntimeException("User is not allowed to edit composition");
            }
            composition.get().setVisibility(visibility);
            compositionRepository.save(composition.get());
        } else {
            throw new RuntimeException("Could not find composition with id " + compositionId);
        }
    }

    @Override
    public void addCompositionEditor(int compositionId, int editorId, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        Optional<User> editor = userRepository.findById(editorId);
        if (composition.isPresent() && editor.isPresent()) {
            if (user.getId() != composition.get().getCreator().getId()) {
                throw new RuntimeException("User is not allowed to edit composition");
            }
            composition.get().getEditors().add(editor.get());
            compositionRepository.save(composition.get());
        } else if (editor.isPresent()) {
            throw new RuntimeException("Could not find composition with id " + compositionId);
        } else {
            throw new RuntimeException("Could not find user with id " + editorId);
        }
    }

    @Override
    public void removeCompositionEditor(int compositionId, int editorId, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        if (composition.isPresent()) {
            if (user.getId() != composition.get().getCreator().getId()) {
                throw new RuntimeException("User is not allowed to edit composition");
            }
            composition.get().getEditors().removeIf(editor -> editor.getId() == editorId);
            compositionRepository.save(composition.get());
        } else {
            throw new RuntimeException("Could not find composition with id " + compositionId);
        }
    }

    @Override
    public CompositionHistoryResponse getCompositionHistory(int compositionId, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        if (composition.isEmpty()) {
            throw new RuntimeException("Could not find composition with id " + compositionId);
        }

        List<Blueprint> blueprints = new ArrayList<>();
        Blueprint currentBlueprint = composition.get().getBlueprint();
        blueprints.add(currentBlueprint);
        while (currentBlueprint.getParent() != null) {
            currentBlueprint = currentBlueprint.getParent();
            blueprints.add(currentBlueprint);
        }

        return new CompositionHistoryResponse(
                blueprints.size(),
                blueprints.stream().map(blueprint -> new BlueprintMiniatureResponse(
                        blueprint.getId(),
                        blueprint.getParent() == null ? null : blueprint.getParent().getId(),
                        commonService.createUserMiniature(
                                blueprint.getCreator(), commonService.isFavourite(blueprint.getCreator(), user)
                        )
                )).toList()
        );
    }

    @Override
    public void deleteComposition(int compositionId, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        Optional<Composition> composition = compositionRepository.findById(compositionId);
        if (composition.isEmpty()) {
            throw new RuntimeException("Could not find composition with id " + compositionId);
        }

        compositionRepository.delete(composition.get());
    }

    private CompositionResponse createCompositionResponse(Composition composition, String authToken) {
        User user = commonService.getUserByAuthToken(authToken);
        User creator = composition.getCreator();
        List<User> editors = composition.getEditors();
        Blueprint blueprint = composition.getBlueprint();
        return new CompositionResponse(
                composition.getId(),
                composition.getName(),
                commonService.isFavourite(composition, user),
                composition.getVisibility(),
                commonService.createUserMiniature(creator, commonService.isFavourite(creator, user)),
                editors.stream().map(
                        editor -> commonService.createUserMiniature(editor, commonService.isFavourite(editor, user))
                ).toList(),
                new BlueprintResponse(
                        blueprint.getId(),
                        blueprint.getParent() == null ? null : blueprint.getParent().getId(),
                        commonService.createUserMiniature(
                                blueprint.getCreator(),
                                commonService.isFavourite(blueprint.getCreator(), user)
                        ),
                        blueprint.getValue()
                )
        );
    }

    private boolean isUserAllowedToEdit(User user, Composition composition) {
        if (user.getId() == composition.getCreator().getId()) {
            return true;
        }
        for (User editor: composition.getEditors()) {
            if (editor.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }
}
