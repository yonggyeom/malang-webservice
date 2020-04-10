package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.users.Users;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class MessagesListResponseDto {
    private Long id;
    private String chatId;
    private String fromUserId;
    private String text;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MessagesListResponseDto(Messages entity) {
        this.id = entity.getId();
        this.chatId               = entity.getChatId()         ;
        this.fromUserId           = entity.getFromUserId()     ;
        this.text                 = entity.getText()           ;
        this.createdDate          = entity.getCreatedDate()    ;
        this.modifiedDate         = entity.getModifiedDate()   ;
    }
}
