package com.malang.webservice.mobile.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String imageUrl;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public PostsUpdateRequestDto(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
