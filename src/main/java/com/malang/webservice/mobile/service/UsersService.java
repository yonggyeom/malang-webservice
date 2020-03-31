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

    @Transactional(readOnly = true)
    public UsersResponseDto findUser(String id) {
        Users entity = usersRepository.findUser(id);

        return new UsersResponseDto(entity);
    }

}
