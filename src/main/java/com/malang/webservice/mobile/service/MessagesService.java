package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.messages.MessagesRepository;
import com.malang.webservice.mobile.web.dto.MessagesListResponseDto;
import com.malang.webservice.mobile.web.dto.MessagesSaveRequestDto;
import com.malang.webservice.mobile.web.dto.UsersListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;

    @Transactional
    public Long save(MessagesSaveRequestDto requestDto) {
        return messagesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Messages saveMessageReturnEntity(MessagesSaveRequestDto requestDto) {
        return messagesRepository.save(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public List<MessagesListResponseDto> findAllDesc(String chatId) {
        return messagesRepository.findAllDesc(chatId).stream()
                .map(MessagesListResponseDto::new)
                .collect(Collectors.toList());
    }
}
