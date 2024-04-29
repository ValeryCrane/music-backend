package com.valerycrane.music.controller;

import com.valerycrane.music.dto.SuccessResponse;
import com.valerycrane.music.dto.sample.SampleCreateResponse;
import com.valerycrane.music.dto.sample.SampleMiniatureResponse;
import com.valerycrane.music.dto.sample.SamplesResponse;
import com.valerycrane.music.service.SampleService;
import com.valerycrane.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public final class SampleController {

    private final SampleService sampleService;

    public SampleController(@Autowired SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping(value = "/sample", produces = "audio/wav")
    public byte[] sample(@RequestParam("id") int id) {
        return sampleService.getSampleById(id);
    }

    @GetMapping(value = "/sample/info")
    public SampleMiniatureResponse sampleInfo(@RequestParam("id") int id) {
        return sampleService.getSampleInfoById(id);
    }

    @PostMapping(
            value = "/sample",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SampleCreateResponse createSample(
            @RequestParam("name") String name,
            @RequestParam("beats") Integer beats,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            int sampleId = sampleService.createSample(name, beats, file.getBytes());
            return new SampleCreateResponse(sampleId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/samples")
    public SamplesResponse getUserSamples(@RequestHeader("Auth") String authToken) {
        return sampleService.getUserSamples(authToken);
    }
}
