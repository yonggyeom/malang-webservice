package com.malang.webservice.mobile.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class UsersFilterUpdateRequestDto {
    private String representativeUserId;
    private String friendType          ;
    private int ageFrom                ;
    private int ageTo                  ;

    @Builder
    public UsersFilterUpdateRequestDto(String representativeUserId, String friendType, int ageFrom, int ageTo) {
        this.representativeUserId = representativeUserId;
        this.friendType = friendType;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
    }

}
