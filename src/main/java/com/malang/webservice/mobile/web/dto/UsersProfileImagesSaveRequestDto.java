package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImages;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class UsersProfileImagesSaveRequestDto {
    private Long id;
    private int seq;
    private String representativeUserId;
    private int representativeYn;
    private String imageUrl;

    @Builder
    public UsersProfileImagesSaveRequestDto(Long id, int seq, String representativeUserId, int representativeYn, String imageUrl) {
        this.id = id;
        this.seq = seq;
        this.representativeUserId = representativeUserId;
        this.representativeYn = representativeYn;
        this.imageUrl = imageUrl;
    }

    public UsersProfileImages toEntity() {
        return UsersProfileImages.builder()
            .seq                   (seq)
            .representativeUserId  (representativeUserId)
            .representativeYn      (representativeYn)
            .imageUrl              (imageUrl)
            .build();
    }

}
