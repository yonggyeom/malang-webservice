package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.PostsService;
import com.malang.webservice.mobile.service.UsersService;
import com.malang.webservice.mobile.web.dto.PostsResponseDto;
import com.malang.webservice.mobile.web.dto.PostsSaveRequestDto;
import com.malang.webservice.mobile.web.dto.PostsUpdateRequestDto;
import com.malang.webservice.mobile.web.dto.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    @GetMapping("/api/v1/users/findUser/{userId}")
    public UsersResponseDto findUser(@PathVariable String userId) {
        return usersService.findUser(userId);
    }

}
