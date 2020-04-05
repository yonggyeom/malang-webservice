package com.malang.webservice.mobile.web.dto;

import com.malang.webservice.mobile.domain.chats.Chats;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImages;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatsSaveRequestDto {
    private String chatId;
    private String senderUserId;
    private String receiverUserId;
    private int approvalYn;

    @Builder
    public ChatsSaveRequestDto(Long id, String chatId, String senderUserId, String receiverUserId, int approvalYn) {
        this.chatId = chatId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.approvalYn = approvalYn;
    }

    public Chats toEntity() {
        return Chats.builder()
            .chatId(chatId)
            .senderUserId(senderUserId)
            .receiverUserId(receiverUserId)
            .approvalYn(approvalYn)
            .build();
    }

}
