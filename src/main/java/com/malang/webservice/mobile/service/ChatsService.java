package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.chats.Chats;
import com.malang.webservice.mobile.domain.chats.ChatsRepository;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImages;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImagesRepository;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatsService {
    private final ChatsRepository chatsRepository;

    @Transactional
    public Long save(ChatsSaveRequestDto requestDto) {
        return chatsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public ChatsResponseDto findChat(String chatId, String senderUserId) {
        Chats entity = chatsRepository.findChat(chatId, senderUserId);

        return new ChatsResponseDto(entity);
    }
}
