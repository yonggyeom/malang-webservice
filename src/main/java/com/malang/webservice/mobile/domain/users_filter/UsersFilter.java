package com.malang.webservice.mobile.domain.users_filter;

import com.malang.webservice.mobile.domain.BaseTimeEntity;
import com.malang.webservice.mobile.web.dto.UsersUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class UsersFilter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String representativeUserId;
    private String friendType          ;
    private int ageFrom                ;
    private int ageTo                  ;

    @Builder
    public UsersFilter(String representativeUserId, String friendType, int ageFrom, int ageTo) {
        this.representativeUserId = representativeUserId;
        this.friendType = friendType;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
    }

}
