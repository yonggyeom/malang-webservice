package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImages;
import com.malang.webservice.mobile.service.UsersProfileImagesService;
import com.malang.webservice.mobile.service.UsersService;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UsersProfileImagesApiController {

    private final UsersProfileImagesService usersProfileImagesService;

    @PostMapping("/api/v1/usersProfileImages")
    public Long save(@RequestBody UsersProfileImagesSaveRequestDto requestDto) {
        return usersProfileImagesService.save(requestDto);
    }

}
