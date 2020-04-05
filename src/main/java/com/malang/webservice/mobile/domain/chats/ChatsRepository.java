package com.malang.webservice.mobile.domain.chats;

import com.malang.webservice.mobile.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatsRepository extends JpaRepository<Chats, Long> {
    @Query("SELECT u FROM Chats u WHERE u.chatId = :chatId AND ROWNUM = 1")
    Chats findChat(@Param("chatId") String chatId);
}
