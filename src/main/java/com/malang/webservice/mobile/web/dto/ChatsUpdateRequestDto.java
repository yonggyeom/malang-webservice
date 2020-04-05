package com.malang.webservice.mobile.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ChatsUpdateRequestDto {
    private String chatId;
    private String senderUserId;
    private String receiverUserId;
    private int approvalYn;

    @Builder
    public ChatsUpdateRequestDto(String chatId, String senderUserId, String receiverUserId, int approvalYn) {
        this.chatId = chatId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.approvalYn = approvalYn;
    }

}
