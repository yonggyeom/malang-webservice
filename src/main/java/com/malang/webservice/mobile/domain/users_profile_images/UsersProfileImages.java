package com.malang.webservice.mobile.domain.users_profile_images;

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
public class UsersProfileImages extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int seq;

    private String representativeUserId;
    private int representativeYn;
    private String imageUrl;

    @Builder
    public UsersProfileImages(int seq, String representativeUserId, int representativeYn, String imageUrl) {
        this.seq = seq;
        this.representativeUserId = representativeUserId;
        this.representativeYn = representativeYn;
        this.imageUrl = imageUrl;
    }

//    public void update(UsersUpdateRequestDto requestDto) {
//        this.birthDate               = requestDto.getBirthDate();
//        this.candyCnt                = requestDto.getCandyCnt();
//        this.googleUserId            = requestDto.getGoogleUserId();
//        this.kakaoUserId             = requestDto.getKakaoUserId();
//        this.naverUserId             = requestDto.getNaverUserId();
//        this.latitude                = requestDto.getLatitude();
//        this.longitude               = requestDto.getLongitude();
//        this.latestRecommendedDate   = requestDto.getLatestRecommendedDate();
//        this.firstRecommendedUserId  = requestDto.getFirstRecommendedUserId();
//        this.secondRecommendedUserId = requestDto.getSecondRecommendedUserId();
//        this.representativeImageUrl  = requestDto.getRepresentativeImageUrl();
//        this.reqRecommendationYn     = requestDto.getReqRecommendationYn();
//        this.acquiredScore           = requestDto.getAcquiredScore();
//        this.maxScoreCnt             = requestDto.getMaxScoreCnt();
//        this.evaluatorCnt            = requestDto.getEvaluatorCnt();
//        this.representativeUserId    = requestDto.getRepresentativeUserId();
//        this.userNickname            = requestDto.getUserNickname();
//        this.userPhoneNumber         = requestDto.getUserPhoneNumber();
//    }

}
