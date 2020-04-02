package com.malang.webservice.mobile.domain.users;

import com.malang.webservice.mobile.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
//    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
//    List<Users> findAllDesc();

//    @Query("select t from Member t where name=:name and age < :age")
//    List<Member> findByNameAndAgeLessThanSQL(@Param("name") String name, @Param("age") int age);

    //@Query("select  u from    users u where   1=1 and     'google' = :snsType and     u.google_user_id = :id union all select  u from    users u where   1=1 and     'naver' = :snsType and     u.naver_user_id = :id union all select  u from    users u where   1=1 and     'kakao' = :snsType and     u.kakao_user_id = :id")
    //@Query("select u.* from users u where 'google'=:snsType and u.google_user_id=:id")
    @Query("SELECT u FROM Users u WHERE 1=1 AND (u.googleUserId=:userId OR u.naverUserId=:userId OR u.kakaoUserId=:userId)")
    Users findUser(@Param("userId") String userId);

    @Query("SELECT u FROM Users u WHERE u.id != :id ORDER BY u.id DESC")
    List<Users> findAllDesc(@Param("id") Long id);
}
