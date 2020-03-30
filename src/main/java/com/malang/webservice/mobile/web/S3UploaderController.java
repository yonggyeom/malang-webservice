package com.malang.webservice.mobile.web;

import com.malang.webservice.mobile.service.PostsService;
import com.malang.webservice.mobile.service.S3UploaderService;
import com.malang.webservice.mobile.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class S3UploaderController {

    private final S3UploaderService s3UploaderService;
    private final PostsService postsService;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
//        String tmpImageUrl = s3UploaderService.upload(multipartFile, "static");

        // 아래 부분에 firlUrl 을 PostService 를 통해 Posts 테이블에 url을 저장한다.
//        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
//                .imageUrl(tmpImageUrl)
//                .build();
//
//        return postsService.updateImageUrl(id, requestDto).toString();
        return s3UploaderService.upload(multipartFile, "static");
    }
}
