package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.stories.Stories;
import com.malang.webservice.mobile.domain.users.Users;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class StoriesListResponseDto {
    private Long id;
    private String representativeUserId;
    private String userPhoneNumber;
    private String uploadedImageUrl;
    private String text;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public StoriesListResponseDto(Stories entity) {
        this.id = entity.getId();
        this.representativeUserId    = entity.getRepresentativeUserId()   ;
        this.userPhoneNumber         = entity.getUserPhoneNumber()        ;
        this.uploadedImageUrl        = entity.getUploadedImageUrl()       ;
        this.text                    = entity.getText()                   ;
        this.createdDate             = entity.getCreatedDate()            ;
        this.modifiedDate            = entity.getModifiedDate()           ;
    }
}
