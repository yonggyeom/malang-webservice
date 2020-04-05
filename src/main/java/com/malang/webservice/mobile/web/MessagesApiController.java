package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.ChatsService;
import com.malang.webservice.mobile.service.MessagesService;
import com.malang.webservice.mobile.web.dto.ChatsResponseDto;
import com.malang.webservice.mobile.web.dto.ChatsSaveRequestDto;
import com.malang.webservice.mobile.web.dto.MessagesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MessagesApiController {

    private final MessagesService messagesService;

    @PostMapping("/api/v1/messages")
    public Long save(@RequestBody MessagesSaveRequestDto requestDto) {
        return messagesService.save(requestDto);
    }


}
