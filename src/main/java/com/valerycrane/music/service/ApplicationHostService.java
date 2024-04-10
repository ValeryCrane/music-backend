package com.valerycrane.music.service;

public interface ApplicationHostService {
    String getHostWithHTTPScheme();
    String getHostWithHTTPSchemeAndPath(String path);
}
