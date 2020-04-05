package com.malang.webservice.mobile.domain.users_profile_images;

import com.malang.webservice.mobile.domain.BaseTimeEntity;
import com.malang.webservice.mobile.web.dto.UsersProfileImagesUpdateRequestDto;
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

    // update
    public void update(UsersProfileImagesUpdateRequestDto requestDto) {
        this.seq                  = requestDto.getSeq();
        this.representativeUserId = requestDto.getRepresentativeUserId();
        this.representativeYn     = requestDto.getRepresentativeYn();
        this.imageUrl             = requestDto.getImageUrl();
    }

}
