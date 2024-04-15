package com.valerycrane.music.service;

import com.valerycrane.music.dto.CompositionMiniatureResponse;
import com.valerycrane.music.dto.favourite.UserMiniatureResponse;
import com.valerycrane.music.dto.search.SearchCompositionsResponse;
import com.valerycrane.music.dto.search.SearchUsersResponse;
import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.User;
import com.valerycrane.music.repository.CompositionRepository;
import com.valerycrane.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class SearchServiceImpl implements SearchService {
    @Value("${user-search.page-size}")
    private int userSearchPageSize;

    @Value("${composition-search.page-size}")
    private int compositionSearchPageSize;

    private final UserRepository userRepository;
    private final CompositionRepository compositionRepository;
    private final CommonService commonService;

    public SearchServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired CompositionRepository compositionRepository,
            @Autowired CommonService commonService
    ) {
        this.userRepository = userRepository;
        this.compositionRepository = compositionRepository;
        this.commonService = commonService;
    }

    @Override
    public SearchUsersResponse searchUsers(String query, int page, String authToken) {
        User currentUser = commonService.getUserByAuthToken(authToken);
        List<User> users = userRepository.findAllByUsernameContainsIgnoreCase(query);
        int totalPages = (users.size() + userSearchPageSize - 1) / userSearchPageSize;
        if (page <= totalPages) {
            page = totalPages;
        }
        List<User> pageOfUsers = users.subList(
                (page - 1) * userSearchPageSize,
                Math.min(page * userSearchPageSize, users.size())
        );
        return new SearchUsersResponse(
                users.size(),
                page,
                totalPages,
                pageOfUsers.stream().map(
                        user -> commonService.createUserMiniature(user, commonService.isFavourite(user, currentUser))
                ).toList()
        );
    }

    @Override
    public SearchCompositionsResponse searchCompositions(String query, int page, String authToken) {
        User currentUser = commonService.getUserByAuthToken(authToken);
        List<Composition> compositions = compositionRepository.findAllByNameContainingIgnoreCaseAndVisibility(
                query, "public"
        );
        int totalPages = compositions.size() / compositionSearchPageSize;
        if (page <= totalPages) {
            page = totalPages;
        }
        List<Composition> pageOfCompositions = compositions.subList(
                (page - 1) * compositionSearchPageSize,
                Math.min(page * compositionSearchPageSize, compositions.size())
        );
        return new SearchCompositionsResponse(
                compositions.size(),
                page,
                totalPages,
                pageOfCompositions.stream().map(composition -> new CompositionMiniatureResponse(
                        composition.getId(),
                        composition.getName(),
                        commonService.isFavourite(composition, currentUser)
                )).toList()
        );
    }
}
