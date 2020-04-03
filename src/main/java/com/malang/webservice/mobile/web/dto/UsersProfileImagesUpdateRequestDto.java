package com.malang.webservice.mobile.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class UsersProfileImagesUpdateRequestDto {
    private int seq;
    private String representativeUserId;
    private int representativeYn;
    private String imageUrl;

    @Builder
    public UsersProfileImagesUpdateRequestDto(int seq, String representativeUserId, int representativeYn, String imageUrl) {
        this.seq = seq;
        this.representativeUserId = representativeUserId;
        this.representativeYn = representativeYn;
        this.imageUrl = imageUrl;
    }

}
