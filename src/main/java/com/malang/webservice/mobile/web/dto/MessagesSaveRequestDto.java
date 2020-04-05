package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class MessagesSaveRequestDto {
    private String chatId;
    private String fromUserId;
    private String text;

    @Builder
    public MessagesSaveRequestDto(String chatId, String fromUserId, String text) {
        this.chatId = chatId;
        this.fromUserId = fromUserId;
        this.text = text;
    }

    public Messages toEntity() {
        return Messages.builder()
            .chatId(chatId)
            .fromUserId(fromUserId)
            .text(text)
            .build();
    }

}
