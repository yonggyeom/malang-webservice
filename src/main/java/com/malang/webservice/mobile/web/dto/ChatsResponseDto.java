package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.chats.Chats;
import com.malang.webservice.mobile.domain.users.Users;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class ChatsResponseDto {

    private Long id;
    private String chatId;
    private String senderUserId;
    private String receiverUserId;
    private int approvalYn;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ChatsResponseDto(Chats entity) {
        this.id           = entity.getId();
        this.chatId                  = entity.getChatId()                 ;
        this.senderUserId            = entity.getSenderUserId()           ;
        this.receiverUserId          = entity.getReceiverUserId()         ;
        this.approvalYn              = entity.getApprovalYn()             ;
        this.createdDate             = entity.getCreatedDate()            ;
        this.modifiedDate            = entity.getModifiedDate()           ;
    }

}
