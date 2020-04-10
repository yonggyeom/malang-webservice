package com.malang.webservice.mobile.domain.messages;

import com.malang.webservice.mobile.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query("SELECT u FROM Messages u WHERE u.chatId = :chatId ORDER BY u.createdDate")
    List<Messages> findAllDesc(@Param("chatId") String chatId);
}
