package com.malang.webservice.mobile.domain.stories;

import com.malang.webservice.mobile.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Stories extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String representativeUserId;
    private String userPhoneNumber;
    private String uploadedImageUrl;
    private String text;

    @Builder
    public Stories(String representativeUserId, String userPhoneNumber, String uploadedImageUrl, String text) {
        this.representativeUserId = representativeUserId;
        this.userPhoneNumber = userPhoneNumber;
        this.uploadedImageUrl = uploadedImageUrl;
        this.text = text;
    }

}
