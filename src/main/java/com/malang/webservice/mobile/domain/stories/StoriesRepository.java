package com.malang.webservice.mobile.domain.stories;

import com.malang.webservice.mobile.domain.messages.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoriesRepository extends JpaRepository<Stories, Long> {
    @Query("SELECT u FROM Stories u WHERE u.representativeUserId != :representativeUserId ORDER BY u.createdDate DESC")
    List<Stories> findAllDesc(@Param("representativeUserId") String representativeUserId);
}
