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

    private String gender         ;
    private int height            ;
    private String bloodType      ;
    private String religionType   ;
    private String drinkingType   ;
    private String smokingType    ;
    private String schoolType     ;
    private String schoolName     ;
    private String jobType        ;
    private String jobName        ;
    private String companyName    ;
    private String characterType  ;
    private String hobbyList      ;
    private String idealTypeList  ;
    private String selfIntroducing;

    @Builder
    public UsersSaveRequestDto(String birthDate, int candyCnt, String googleUserId, String kakaoUserId, String naverUserId, Double latitude, Double longitude, Timestamp latestRecommendedDate, String firstRecommendedUserId, String secondRecommendedUserId, String representativeImageUrl, int reqRecommendationYn, int acquiredScore, int maxScoreCnt, int evaluatorCnt, String representativeUserId, String userNickname, String userPhoneNumber, String gender, int height, String bloodType, String religionType, String drinkingType, String smokingType, String schoolType, String schoolName, String jobType, String jobName, String companyName, String characterType, String hobbyList, String idealTypeList, String selfIntroducing) {
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

        this.gender = gender;
        this.height = height;
        this.bloodType = bloodType;
        this.religionType = religionType;
        this.drinkingType = drinkingType;
        this.smokingType = smokingType;
        this.schoolType = schoolType;
        this.schoolName = schoolName;
        this.jobType = jobType;
        this.jobName = jobName;
        this.companyName = companyName;
        this.characterType = characterType;
        this.hobbyList = hobbyList;
        this.idealTypeList = idealTypeList;
        this.selfIntroducing = selfIntroducing;
    }

//    @Builder
//    public UsersSaveRequestDto(String birthDate, int candyCnt, String googleUserId, String kakaoUserId, String naverUserId, Double latitude, Double longitude, Timestamp latestRecommendedDate, String firstRecommendedUserId, String secondRecommendedUserId, String representativeImageUrl, int reqRecommendationYn, int acquiredScore, int maxScoreCnt, int evaluatorCnt, String representativeUserId, String userNickname, String userPhoneNumber) {
//        this.birthDate = birthDate;
//        this.candyCnt = candyCnt;
//        this.googleUserId = googleUserId;
//        this.kakaoUserId = kakaoUserId;
//        this.naverUserId = naverUserId;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.latestRecommendedDate = latestRecommendedDate;
//        this.firstRecommendedUserId = firstRecommendedUserId;
//        this.secondRecommendedUserId = secondRecommendedUserId;
//        this.representativeImageUrl = representativeImageUrl;
//        this.reqRecommendationYn = reqRecommendationYn;
//        this.acquiredScore = acquiredScore;
//        this.maxScoreCnt = maxScoreCnt;
//        this.evaluatorCnt = evaluatorCnt;
//        this.representativeUserId = representativeUserId;
//        this.userNickname = userNickname;
//        this.userPhoneNumber = userPhoneNumber;
//    }

    public Users toEntity() {
        return Users.builder()
            .birthDate              (birthDate)
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
            .gender                 (gender)
            .height                 (height)
            .bloodType              (bloodType)
            .religionType           (religionType)
            .drinkingType           (drinkingType)
            .smokingType            (smokingType)
            .schoolType             (schoolType)
            .schoolName             (schoolName)
            .jobType                (jobType)
            .jobName                (jobName)
            .companyName            (companyName)
            .characterType          (characterType)
            .hobbyList              (hobbyList)
            .idealTypeList          (idealTypeList)
            .selfIntroducing        (selfIntroducing)
            .build();
    }

}
