package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImages;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class UsersProfileImagesListResponseDto {
    private Long id;
    private int seq;
    private String representativeUserId;
    private int representativeYn;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public UsersProfileImagesListResponseDto(UsersProfileImages entity) {
        this.id = entity.getId();
        this.seq                  = entity.getSeq()                 ;
        this.representativeUserId = entity.getRepresentativeUserId();
        this.representativeYn     = entity.getRepresentativeYn()    ;
        this.imageUrl             = entity.getImageUrl()            ;
        this.createdDate          = entity.getCreatedDate()         ;
        this.modifiedDate         = entity.getModifiedDate()        ;
    }
}
