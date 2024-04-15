package com.valerycrane.music.service;

import com.valerycrane.music.dto.search.SearchCompositionsResponse;
import com.valerycrane.music.dto.search.SearchUsersResponse;

public interface SearchService {
    SearchUsersResponse searchUsers(String query, int page, String authToken);
    SearchCompositionsResponse searchCompositions(String query, int page, String authToken);
}
