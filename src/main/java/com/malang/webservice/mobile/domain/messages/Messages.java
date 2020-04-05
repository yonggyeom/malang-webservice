package com.malang.webservice.mobile.domain.messages;

import com.malang.webservice.mobile.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Messages extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;
    private String fromUserId;
    private String text;

    @Builder
    public Messages(String chatId, String fromUserId, String text) {
        this.chatId = chatId;
        this.fromUserId = fromUserId;
        this.text = text;
    }

}
