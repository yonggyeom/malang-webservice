package com.malang.webservice.mobile.domain.users;

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
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String birthDate;
    private int candyCnt               ;
    private String googleUserId           ;
    private String kakaoUserId            ;
    private String naverUserId            ;
    private Double latitude               ;
    private Double longitude              ;
    private Timestamp latestRecommendedDate  ;
    private String firstRecommendedUserId ;
    private String secondRecommendedUserId;
    private String representativeImageUrl ;
    private int reqRecommendationYn    ;
    private int acquiredScore          ;
    private int maxScoreCnt            ;
    private int evaluatorCnt           ;
    private String representativeUserId   ;
    private String userNickname           ;
    private String userPhoneNumber        ;

    @Builder
    public Users(String birthDate, int candyCnt, String googleUserId, String kakaoUserId, String naverUserId, Double latitude, Double longitude, Timestamp latestRecommendedDate, String firstRecommendedUserId, String secondRecommendedUserId, String representativeImageUrl, int reqRecommendationYn, int acquiredScore, int maxScoreCnt, int evaluatorCnt, String representativeUserId, String userNickname, String userPhoneNumber) {
        this.birthDate = birthDate;
        this.candyCnt = candyCnt;
        this.googleUserId = googleUserId;
        this.kakaoUserId = kakaoUserId;
        this.naverUserId = naverUserId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latestRecommendedDate = latestRecommendedDate;
        this.firstRecommendedUserId = firstRecommendedUserId;
        this.secondRecommendedUserId = secondRecommendedUserId;
        this.representativeImageUrl = representativeImageUrl;
        this.reqRecommendationYn = reqRecommendationYn;
        this.acquiredScore = acquiredScore;
        this.maxScoreCnt = maxScoreCnt;
        this.evaluatorCnt = evaluatorCnt;
        this.representativeUserId = representativeUserId;
        this.userNickname = userNickname;
        this.userPhoneNumber = userPhoneNumber;
    }

    public void update(UsersUpdateRequestDto requestDto) {
        this.birthDate               = requestDto.getBirthDate();
        this.candyCnt                = requestDto.getCandyCnt();
        this.googleUserId            = requestDto.getGoogleUserId();
        this.kakaoUserId             = requestDto.getKakaoUserId();
        this.naverUserId             = requestDto.getNaverUserId();
        this.latitude                = requestDto.getLatitude();
        this.longitude               = requestDto.getLongitude();
        this.latestRecommendedDate   = requestDto.getLatestRecommendedDate();
        this.firstRecommendedUserId  = requestDto.getFirstRecommendedUserId();
        this.secondRecommendedUserId = requestDto.getSecondRecommendedUserId();
        this.representativeImageUrl  = requestDto.getRepresentativeImageUrl();
        this.reqRecommendationYn     = requestDto.getReqRecommendationYn();
        this.acquiredScore           = requestDto.getAcquiredScore();
        this.maxScoreCnt             = requestDto.getMaxScoreCnt();
        this.evaluatorCnt            = requestDto.getEvaluatorCnt();
        this.representativeUserId    = requestDto.getRepresentativeUserId();
        this.userNickname            = requestDto.getUserNickname();
        this.userPhoneNumber         = requestDto.getUserPhoneNumber();
    }

//    public void update(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//
//    public void updateImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }

}
