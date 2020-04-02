package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.posts.Posts;
import com.malang.webservice.mobile.domain.posts.PostsRepository;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users.UsersRepository;
import com.malang.webservice.mobile.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional
    public Long save(UsersSaveRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UsersUpdateRequestDto requestDto) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        users.update(requestDto);

        return id;
    }

    @Transactional(readOnly = true)
    public UsersResponseDto findUser(String userId) {
        Users entity = usersRepository.findUser(userId);

        return new UsersResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<UsersListResponseDto> findAllDesc(Long id) {
        return usersRepository.findAllDesc(id).stream()
                .map(UsersListResponseDto::new)
                .collect(Collectors.toList());
    }

}
