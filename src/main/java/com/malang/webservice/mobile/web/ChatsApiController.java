package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.ChatsService;
import com.malang.webservice.mobile.service.UsersProfileImagesService;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatsApiController {

    private final ChatsService chatsService;

    @PostMapping("/api/v1/chats")
    public Long save(@RequestBody ChatsSaveRequestDto requestDto) {
        return chatsService.save(requestDto);
    }

    @GetMapping("/api/v1/chats/findChat/{chatId}/{senderUserId}")
    public ChatsResponseDto findChat(@PathVariable String chatId, @PathVariable String senderUserId) {
        return chatsService.findChat(chatId, senderUserId);
    }

    @PutMapping("/api/v1/chats/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody ChatsUpdateRequestDto requestDto) {
        return chatsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/chats/findAllChatForMe/{senderUserId}")
    public List<ChatsListResponseDto> findAllUserExceptMe(@PathVariable String senderUserId) {
        return chatsService.findAllUserExceptMe(senderUserId);
    }
}
