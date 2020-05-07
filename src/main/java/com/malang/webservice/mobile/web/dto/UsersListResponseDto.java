package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.posts.Posts;
import com.malang.webservice.mobile.domain.users.Users;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class UsersListResponseDto {
    private Long id;
    private String birthDate ;
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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

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

    private int activationYn      ;

    public UsersListResponseDto(Users entity) {
        this.id = entity.getId();
        this.birthDate               = entity.getBirthDate()              ;
        this.candyCnt                = entity.getCandyCnt()               ;
        this.googleUserId            = entity.getGoogleUserId()           ;
        this.kakaoUserId             = entity.getKakaoUserId()            ;
        this.naverUserId             = entity.getNaverUserId()            ;
        this.latitude                = entity.getLatitude()               ;
        this.longitude               = entity.getLongitude()              ;
        this.latestRecommendedDate   = entity.getLatestRecommendedDate()  ;
        this.firstRecommendedUserId  = entity.getFirstRecommendedUserId() ;
        this.secondRecommendedUserId = entity.getSecondRecommendedUserId();
        this.representativeImageUrl  = entity.getRepresentativeImageUrl() ;
        this.reqRecommendationYn     = entity.getReqRecommendationYn()    ;
        this.acquiredScore           = entity.getAcquiredScore()          ;
        this.maxScoreCnt             = entity.getMaxScoreCnt()            ;
        this.evaluatorCnt            = entity.getEvaluatorCnt()           ;
        this.representativeUserId    = entity.getRepresentativeUserId()   ;
        this.userNickname            = entity.getUserNickname()           ;
        this.userPhoneNumber         = entity.getUserPhoneNumber()        ;
        this.createdDate             = entity.getCreatedDate()            ;
        this.modifiedDate            = entity.getModifiedDate()           ;

        this.gender          = entity.getGender();
        this.height          = entity.getHeight();
        this.bloodType       = entity.getBloodType();
        this.religionType    = entity.getReligionType();
        this.drinkingType    = entity.getDrinkingType();
        this.smokingType     = entity.getSmokingType();
        this.schoolType      = entity.getSchoolType();
        this.schoolName      = entity.getSchoolName();
        this.jobType         = entity.getJobType();
        this.jobName         = entity.getJobName();
        this.companyName     = entity.getCompanyName();
        this.characterType   = entity.getCharacterType();
        this.hobbyList       = entity.getHobbyList();
        this.idealTypeList   = entity.getIdealTypeList();
        this.selfIntroducing = entity.getSelfIntroducing();

        this.activationYn    = entity.getActivationYn();
    }
}
