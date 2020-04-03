package com.malang.webservice.mobile.domain.users_profile_images;

import com.malang.webservice.mobile.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersProfileImagesRepository extends JpaRepository<UsersProfileImages, Long> {
//    @Query("SELECT u FROM Users u WHERE 1=1 AND (u.googleUserId=:userId OR u.naverUserId=:userId OR u.kakaoUserId=:userId)")
//    UsersProfileImages findUser(@Param("userId") String userId);
//
//    @Query("SELECT u FROM Users u WHERE u.id != :id ORDER BY u.id DESC")
//    List<UsersProfileImages> findAllDesc(@Param("id") Long id);

    @Query("SELECT u FROM UsersProfileImages u WHERE u.representativeUserId = :representativeUserId ORDER BY u.seq")
    List<UsersProfileImages> findAllMyProfileImages(@Param("representativeUserId") String representativeUserId);

    @Query("SELECT u FROM UsersProfileImages u WHERE u.representativeUserId = :representativeUserId AND u.seq = :seq")
    UsersProfileImages findUsersProfileImages(@Param("representativeUserId") String representativeUserId, @Param("seq") int seq);
}
