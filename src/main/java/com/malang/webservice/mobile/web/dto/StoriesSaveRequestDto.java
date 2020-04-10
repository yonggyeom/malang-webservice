package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.stories.Stories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoriesSaveRequestDto {
    private String representativeUserId;
    private String userPhoneNumber;
    private String uploadedImageUrl;
    private String text;

    @Builder
    public StoriesSaveRequestDto(String representativeUserId, String userPhoneNumber, String uploadedImageUrl, String text) {
        this.representativeUserId = representativeUserId;
        this.userPhoneNumber = userPhoneNumber;
        this.uploadedImageUrl = uploadedImageUrl;
        this.text = text;
    }

    public Stories toEntity() {
        return Stories.builder()
            .representativeUserId(representativeUserId)
            .userPhoneNumber(userPhoneNumber)
            .uploadedImageUrl(uploadedImageUrl)
            .text(text)
            .build();
    }

}
