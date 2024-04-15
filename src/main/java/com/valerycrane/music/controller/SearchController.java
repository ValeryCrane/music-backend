package com.valerycrane.music.controller;

import com.valerycrane.music.dto.search.SearchCompositionsResponse;
import com.valerycrane.music.dto.search.SearchUsersResponse;
import com.valerycrane.music.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class SearchController {

    private final SearchService searchService;

    public SearchController(@Autowired SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search/users")
    public SearchUsersResponse searchUsers(
            @RequestHeader("Auth") String authToken,
            @RequestParam("query") String query,
            @RequestParam("page") int page
    ) {
        return searchService.searchUsers(query, page, authToken);
    }

    @GetMapping("/search/compositions")
    public SearchCompositionsResponse searchCompositions(
            @RequestHeader("Auth") String authToken,
            @RequestParam("query") String query,
            @RequestParam("page") int page
    ) {
        return searchService.searchCompositions(query, page, authToken);
    }
}
