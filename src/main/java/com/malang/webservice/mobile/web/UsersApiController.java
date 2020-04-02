package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.PostsService;
import com.malang.webservice.mobile.service.UsersService;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/api/v1/users")
    public Long save(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.save(requestDto);
    }

    @PutMapping("/api/v1/users/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.update(id, requestDto);
    }

    @GetMapping("/api/v1/users/findUser/{userId}")
    public UsersResponseDto findUser(@PathVariable String userId) {
        return usersService.findUser(userId);
    }

}
