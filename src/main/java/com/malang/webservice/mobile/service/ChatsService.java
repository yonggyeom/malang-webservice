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

    @Transactional
    public Long update(Long id, ChatsUpdateRequestDto requestDto) {
        Chats chats = chatsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다. id=" + id));

        chats.update(requestDto);

        return id;
    }

    @Transactional(readOnly = true)
    public List<ChatsListResponseDto> findAllUserExceptMe(String senderUserId) {
        return chatsRepository.findAllUserExceptMe(senderUserId).stream()
                .map(ChatsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
