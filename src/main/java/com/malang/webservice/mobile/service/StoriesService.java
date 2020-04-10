package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.messages.MessagesRepository;
import com.malang.webservice.mobile.domain.stories.StoriesRepository;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoriesService {
    private final StoriesRepository storiesRepository;

    @Transactional
    public Long save(StoriesSaveRequestDto requestDto) {
        return storiesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<StoriesListResponseDto> findAllStories(String representativeUserId) {
        return storiesRepository.findAllStories(representativeUserId).stream()
                .map(StoriesListResponseDto::new)
                .collect(Collectors.toList());
    }
}
