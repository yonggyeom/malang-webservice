package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.posts.Posts;
import com.malang.webservice.mobile.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private Long id;
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
    public UsersSaveRequestDto(Long id, int candyCnt, String googleUserId, String kakaoUserId, String naverUserId, Double latitude, Double longitude, Timestamp latestRecommendedDate, String firstRecommendedUserId, String secondRecommendedUserId, String representativeImageUrl, int reqRecommendationYn, int acquiredScore, int maxScoreCnt, int evaluatorCnt, String representativeUserId, String userNickname, String userPhoneNumber) {
        this.id = id;
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

    public Users toEntity() {
        return Users.builder()
            .candyCnt               (candyCnt)
            .googleUserId           (googleUserId)
            .kakaoUserId            (kakaoUserId)
            .naverUserId            (naverUserId)
            .latitude               (latitude)
            .longitude              (longitude)
            .latestRecommendedDate  (latestRecommendedDate)
            .firstRecommendedUserId (firstRecommendedUserId)
            .secondRecommendedUserId(secondRecommendedUserId)
            .representativeImageUrl (representativeImageUrl)
            .reqRecommendationYn    (reqRecommendationYn)
            .acquiredScore          (acquiredScore)
            .maxScoreCnt            (maxScoreCnt)
            .evaluatorCnt           (evaluatorCnt)
            .representativeUserId   (representativeUserId)
            .userNickname           (userNickname)
            .userPhoneNumber        (userPhoneNumber)
            .build();
    }

}
