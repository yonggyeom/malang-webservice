package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users.UsersRepository;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImagesRepository;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsersProfileImagesService {
    private final UsersProfileImagesRepository usersProfileImagesRepository;

    @Transactional
    public Long save(UsersProfileImagesSaveRequestDto requestDto) {
        return usersProfileImagesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<UsersProfileImagesListResponseDto> findAllMyProfileImages(String representativeUserId) {
        return usersProfileImagesRepository.findAllMyProfileImages(representativeUserId).stream()
                .map(UsersProfileImagesListResponseDto::new)
                .collect(Collectors.toList());
    }

}
