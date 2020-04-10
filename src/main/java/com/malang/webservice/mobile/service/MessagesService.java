package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.messages.MessagesRepository;
import com.malang.webservice.mobile.web.dto.MessagesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
