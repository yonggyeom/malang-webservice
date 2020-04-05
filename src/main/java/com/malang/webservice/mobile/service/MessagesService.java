package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.chats.Chats;
import com.malang.webservice.mobile.domain.chats.ChatsRepository;
import com.malang.webservice.mobile.domain.messages.MessagesRepository;
import com.malang.webservice.mobile.web.dto.ChatsResponseDto;
import com.malang.webservice.mobile.web.dto.ChatsSaveRequestDto;
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

}
