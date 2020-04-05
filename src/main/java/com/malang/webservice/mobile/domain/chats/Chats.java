package com.malang.webservice.mobile.domain.chats;

import com.malang.webservice.mobile.domain.BaseTimeEntity;
import com.malang.webservice.mobile.web.dto.UsersProfileImagesUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Chats extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;
    private String senderUserId;
    private String receiverUserId;
    private int approvalYn;

    @Builder
    public Chats(Long id, String chatId, String senderUserId, String receiverUserId, int approvalYn) {
        this.chatId = chatId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.approvalYn = approvalYn;
    }

}
