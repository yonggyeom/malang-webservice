package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.ChatsService;
import com.malang.webservice.mobile.service.MessagesService;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MessagesApiController {

    private final MessagesService messagesService;

    @PostMapping("/api/v1/messages")
    public Long save(@RequestBody MessagesSaveRequestDto requestDto) {
        return messagesService.save(requestDto);
    }

    @GetMapping("/api/v1/messages/findAllMyMessages/{chatId}")
    public List<MessagesListResponseDto> findAllUserExceptMe(@PathVariable String chatId) {
        return messagesService.findAllDesc(chatId);
    }
}
