package com.valerycrane.music.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public final class ApplicationHostServiceImpl implements ApplicationHostService {

    @Value("${host}")
    private String host;

    @Override
    public String getHostWithHTTPScheme() {
        return "http://" + host + ":80";
    }

    @Override
    public String getHostWithHTTPSchemeAndPath(String path) {
        return getHostWithHTTPScheme() + path;
    }
}
