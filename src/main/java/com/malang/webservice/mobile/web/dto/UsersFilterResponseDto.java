package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.users_filter.UsersFilter;
import lombok.Getter;

@Getter
public class UsersFilterResponseDto {

    private Long id;
    private String representativeUserId;
    private String friendType          ;
    private int ageFrom                ;
    private int ageTo                  ;

    public UsersFilterResponseDto(UsersFilter entity) {
        this.id           = entity.getId();
        this.representativeUserId = entity.getRepresentativeUserId();
        this.friendType           = entity.getFriendType()          ;
        this.ageFrom              = entity.getAgeFrom()             ;
        this.ageTo                = entity.getAgeTo()               ;
    }

}
