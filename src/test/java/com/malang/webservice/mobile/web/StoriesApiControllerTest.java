package com.malang.webservice.mobile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.messages.MessagesRepository;
import com.malang.webservice.mobile.domain.stories.Stories;
import com.malang.webservice.mobile.domain.stories.StoriesRepository;
import com.malang.webservice.mobile.web.dto.MessagesSaveRequestDto;
import com.malang.webservice.mobile.web.dto.StoriesSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoriesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StoriesRepository storiesRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        storiesRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Stories_등록된다() throws Exception {
        String uploadedImageUrl = "uploadedImageUrl";

        //given
        StoriesSaveRequestDto requestDto = StoriesSaveRequestDto.builder()
                .representativeUserId("representativeUserId")
                .userPhoneNumber("userPhoneNumber")
                .uploadedImageUrl(uploadedImageUrl)
                .text("text")
                .build();

        String url = "http://localhost:" + port + "/api/v1/stories";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Stories> all = storiesRepository.findAll();
        assertThat(all.get(0).getUploadedImageUrl()).isEqualTo(uploadedImageUrl);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Stories_전체조회된다() throws Exception {
        String uploadedImageUrl = "uploadedImageUrl";

        //given
        Stories savedStories = storiesRepository.save(Stories.builder()
                .representativeUserId("representativeUserId")
                .userPhoneNumber("userPhoneNumber")
                .uploadedImageUrl(uploadedImageUrl)
                .text("text")
                .build());

        String getRepresentativeUserId = savedStories.getRepresentativeUserId();

        String url = "http://localhost:" + port + "/api/v1/stories/findAllStoriesExceptMe/" + getRepresentativeUserId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<Stories> all = storiesRepository.findAll();
        assertThat(all.get(0).getUploadedImageUrl()).isEqualTo(uploadedImageUrl);
    }
}
