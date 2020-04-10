package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.MessagesService;
import com.malang.webservice.mobile.service.StoriesService;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoriesApiController {

    private final StoriesService storiesService;

    @PostMapping("/api/v1/stories")
    public Long save(@RequestBody StoriesSaveRequestDto requestDto) {
        return storiesService.save(requestDto);
    }

    @GetMapping("/api/v1/stories/findAllStories/{representativeUserId}")
    public List<StoriesListResponseDto> findAllStories(@PathVariable String representativeUserId) {
        return storiesService.findAllStories(representativeUserId);
    }

}
