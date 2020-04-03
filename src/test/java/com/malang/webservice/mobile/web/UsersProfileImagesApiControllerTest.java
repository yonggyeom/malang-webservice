package com.malang.webservice.mobile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users.UsersRepository;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImages;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImagesRepository;
import com.malang.webservice.mobile.web.dto.UsersProfileImagesSaveRequestDto;
import com.malang.webservice.mobile.web.dto.UsersSaveRequestDto;
import com.malang.webservice.mobile.web.dto.UsersUpdateRequestDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersProfileImagesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersProfileImagesRepository usersProfileImagesRepository;

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
        usersProfileImagesRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void UsersProfileImages_등록된다() throws Exception {
        String imageUrl = "imageUrl";

        //given
        UsersProfileImagesSaveRequestDto requestDto = UsersProfileImagesSaveRequestDto.builder()
                .seq(1)
                .representativeUserId("googleUserId")
                .representativeYn(1)
                .imageUrl(imageUrl)
                .build();

        String url = "http://localhost:" + port + "/api/v1/usersProfileImages";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<UsersProfileImages> all = usersProfileImagesRepository.findAll();
        assertThat(all.get(0).getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    @WithMockUser(roles="USER")
    public void UsersProfileImages_전체조회된다() throws Exception {
        String imageUrl = "imageUrl";

        //given
        UsersProfileImages savedUsers = usersProfileImagesRepository.save(UsersProfileImages.builder()
                .seq(1)
                .representativeUserId("googleUserId")
                .representativeYn(1)
                .imageUrl(imageUrl)
                .build());

        String getRepresentativeUserId = savedUsers.getRepresentativeUserId();

        String url = "http://localhost:" + port + "/api/v1/usersProfileImages/findAllMyProfileImages/" + getRepresentativeUserId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<UsersProfileImages> all = usersProfileImagesRepository.findAll();
        assertThat(all.get(0).getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Users_조회된다() throws Exception {
        String imageUrl = "imageUrl";

        //given
        UsersProfileImages savedUsers = usersProfileImagesRepository.save(UsersProfileImages.builder()
                .seq(1)
                .representativeUserId("googleUserId")
                .representativeYn(1)
                .imageUrl(imageUrl)
                .build());

        String getRepresentativeUserId = savedUsers.getRepresentativeUserId();
        int getSeq = savedUsers.getSeq();

        String url = "http://localhost:" + port + "/api/v1/usersProfileImages/findUsersProfileImages/" + getRepresentativeUserId + "/" + getSeq;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<UsersProfileImages> all = usersProfileImagesRepository.findAll();
        assertThat(all.get(0).getImageUrl()).isEqualTo(imageUrl);
    }

}
