package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.users_filter.UsersFilter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersFilterSaveRequestDto {
    private String representativeUserId;
    private String friendType          ;
    private int ageFrom                ;
    private int ageTo                  ;

    @Builder
    public UsersFilterSaveRequestDto(String representativeUserId, String friendType, int ageFrom, int ageTo) {
        this.representativeUserId = representativeUserId;
        this.friendType = friendType;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
    }

    public UsersFilter toEntity() {
        return UsersFilter.builder()
            .representativeUserId(representativeUserId)
            .friendType          (friendType)
            .ageFrom             (ageFrom)
            .ageTo               (ageTo)
            .build();
    }

}
