package com.malang.webservice.mobile.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
//    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
//    List<Users> findAllDesc();

//    @Query("select t from Member t where name=:name and age < :age")
//    List<Member> findByNameAndAgeLessThanSQL(@Param("name") String name, @Param("age") int age);

    @Query("select  u.id\n" +
            ", u.candy_cnt\n" +
            ", u.google_user_id\n" +
            ", u.kakao_user_id\n" +
            ", u.naver_user_id\n" +
            ", u.latitude\n" +
            ", u.longitude\n" +
            ", u.latest_recommended_date\n" +
            ", u.first_recommended_user_id\n" +
            ", u.second_recommended_user_id\n" +
            ", u.representative_image_url\n" +
            ", u.req_recommendation_yn\n" +
            ", u.acquired_score\n" +
            ", u.max_score_cnt\n" +
            ", u.evaluator_cnt\n" +
            ", u.representative_user_id\n" +
            ", u.user_nickname\n" +
            ", u.user_phone_number\n" +
            "from    users u\n" +
            "where   1=1\n" +
            "and     'google' = :snsType\n" +
            "and     u.google_user_id = :id\n" +
            "union all\n" +
            "select  u.id\n" +
            ", u.candy_cnt\n" +
            ", u.google_user_id\n" +
            ", u.kakao_user_id\n" +
            ", u.naver_user_id\n" +
            ", u.latitude\n" +
            ", u.longitude\n" +
            ", u.latest_recommended_date\n" +
            ", u.first_recommended_user_id\n" +
            ", u.second_recommended_user_id\n" +
            ", u.representative_image_url\n" +
            ", u.req_recommendation_yn\n" +
            ", u.acquired_score\n" +
            ", u.max_score_cnt\n" +
            ", u.evaluator_cnt\n" +
            ", u.representative_user_id\n" +
            ", u.user_nickname\n" +
            ", u.user_phone_number  \n" +
            "from    users u\n" +
            "where   1=1\n" +
            "and     'naver' = :snsType\n" +
            "and     u.naver_user_id = :id\n" +
            "union all\n" +
            "select  u.id\n" +
            ", u.candy_cnt\n" +
            ", u.google_user_id\n" +
            ", u.kakao_user_id\n" +
            ", u.naver_user_id\n" +
            ", u.latitude\n" +
            ", u.longitude\n" +
            ", u.latest_recommended_date\n" +
            ", u.first_recommended_user_id\n" +
            ", u.second_recommended_user_id\n" +
            ", u.representative_image_url\n" +
            ", u.req_recommendation_yn\n" +
            ", u.acquired_score\n" +
            ", u.max_score_cnt\n" +
            ", u.evaluator_cnt\n" +
            ", u.representative_user_id\n" +
            ", u.user_nickname\n" +
            ", u.user_phone_number  \n" +
            "from    users u\n" +
            "where   1=1\n" +
            "and     'kakao' = :snsType\n" +
            "and     u.kakao_user_id = :id")
    Users findUser(@Param("snsType") String snsType, @Param("id") String id);
}
