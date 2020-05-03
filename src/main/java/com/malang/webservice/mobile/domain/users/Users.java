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

    private String gender         ;
    @Column(nullable = true)
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
    public Users(Long id, String birthDate, int candyCnt, String googleUserId, String kakaoUserId, String naverUserId, Double latitude, Double longitude, Timestamp latestRecommendedDate, String firstRecommendedUserId, String secondRecommendedUserId, String representativeImageUrl, int reqRecommendationYn, int acquiredScore, int maxScoreCnt, int evaluatorCnt, String representativeUserId, String userNickname, String userPhoneNumber, String gender, int height, String bloodType, String religionType, String drinkingType, String smokingType, String schoolType, String schoolName, String jobType, String jobName, String companyName, String characterType, String hobbyList, String idealTypeList, String selfIntroducing) {
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
//    public Users(String birthDate, int candyCnt, String googleUserId, String kakaoUserId, String naverUserId, Double latitude, Double longitude, Timestamp latestRecommendedDate, String firstRecommendedUserId, String secondRecommendedUserId, String representativeImageUrl, int reqRecommendationYn, int acquiredScore, int maxScoreCnt, int evaluatorCnt, String representativeUserId, String userNickname, String userPhoneNumber) {
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

        this.gender          = requestDto.getGender();
        this.height          = requestDto.getHeight();
        this.bloodType       = requestDto.getBloodType();
        this.religionType    = requestDto.getReligionType();
        this.drinkingType    = requestDto.getDrinkingType();
        this.smokingType     = requestDto.getSmokingType();
        this.schoolType      = requestDto.getSchoolType();
        this.schoolName      = requestDto.getSchoolName();
        this.jobType         = requestDto.getJobType();
        this.jobName         = requestDto.getJobName();
        this.companyName     = requestDto.getCompanyName();
        this.characterType   = requestDto.getCharacterType();
        this.hobbyList       = requestDto.getHobbyList();
        this.idealTypeList   = requestDto.getIdealTypeList();
        this.selfIntroducing = requestDto.getSelfIntroducing();
    }

    public void updateScore(int isLiked) {
        if(isLiked == 1){
            this.acquiredScore           = this.acquiredScore + 1;
            this.evaluatorCnt            = this.evaluatorCnt  + 1;
        }else{
            this.evaluatorCnt            = this.evaluatorCnt  + 1;
        }

        if(this.evaluatorCnt == this.maxScoreCnt){
            this.reqRecommendationYn = 0;
        }
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
