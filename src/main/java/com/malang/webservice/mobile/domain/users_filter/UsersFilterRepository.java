package com.malang.webservice.mobile.domain.users_filter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersFilterRepository extends JpaRepository<UsersFilter, Long> {
    @Query("SELECT u FROM UsersFilter u WHERE 1=1 AND u.representativeUserId = :representativeUserId")
    UsersFilter findUserFilter(@Param("representativeUserId") String representativeUserId);
}
