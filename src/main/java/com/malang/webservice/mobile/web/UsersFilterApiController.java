package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.UsersFilterService;
import com.malang.webservice.mobile.web.dto.*;
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

    @GetMapping("/api/v1/usersFilter/findUser/{representativeUserId}")
    public UsersFilterResponseDto findUserFilter(@PathVariable String representativeUserId) {
        return usersFilterService.findUserFilter(representativeUserId);
    }

    @GetMapping("/api/v1/usersFilter/find/{id}")
    public UsersFilterResponseDto findById(@PathVariable Long id) {
        return usersFilterService.findById(id);
    }

    @PutMapping("/api/v1/usersFilter/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody UsersFilterUpdateRequestDto requestDto) {
        return usersFilterService.update(id, requestDto);
    }
}
