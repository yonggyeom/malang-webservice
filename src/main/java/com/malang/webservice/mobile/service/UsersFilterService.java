package com.malang.webservice.mobile.service;

import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users_filter.UsersFilter;
import com.malang.webservice.mobile.domain.users_filter.UsersFilterRepository;
import com.malang.webservice.mobile.web.dto.UsersFilterResponseDto;
import com.malang.webservice.mobile.web.dto.UsersFilterSaveRequestDto;
import com.malang.webservice.mobile.web.dto.UsersFilterUpdateRequestDto;
import com.malang.webservice.mobile.web.dto.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersFilterService {
    private final UsersFilterRepository usersFilterRepository;

    @Transactional
    public Long save(UsersFilterSaveRequestDto requestDto) {
        return usersFilterRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public UsersFilterResponseDto findUserFilter(String representativeUserId) {
        UsersFilter entity = usersFilterRepository.findUserFilter(representativeUserId);

        return new UsersFilterResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public UsersFilterResponseDto findById(Long id) {
        UsersFilter entity = usersFilterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UsersFilterResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, UsersFilterUpdateRequestDto requestDto) {
        UsersFilter usersFilter = usersFilterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        usersFilter.update(requestDto);

        return id;
    }
}
