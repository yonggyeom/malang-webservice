package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.UsersFilterService;
import com.malang.webservice.mobile.web.dto.UsersFilterResponseDto;
import com.malang.webservice.mobile.web.dto.UsersFilterSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UsersFilterApiController {

    private final UsersFilterService usersFilterService;

    @PostMapping("/api/v1/usersFilter")
    public Long save(@RequestBody UsersFilterSaveRequestDto requestDto) {
        return usersFilterService.save(requestDto);
    }

    @GetMapping("/api/v1/usersFilter/find/{id}")
    public UsersFilterResponseDto findById(@PathVariable Long id) {
        return usersFilterService.findById(id);
    }
}
