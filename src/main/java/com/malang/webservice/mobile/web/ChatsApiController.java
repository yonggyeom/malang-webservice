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

}
