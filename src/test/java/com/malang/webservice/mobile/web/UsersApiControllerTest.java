package com.malang.webservice.mobile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malang.webservice.mobile.domain.posts.Posts;
import com.malang.webservice.mobile.domain.posts.PostsRepository;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users.UsersRepository;
import com.malang.webservice.mobile.web.dto.PostsSaveRequestDto;
import com.malang.webservice.mobile.web.dto.PostsUpdateRequestDto;
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
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;

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
        usersRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Users_조회된다() throws Exception {
        String googleUserId = "googleUserId";
        String userNickname = "userNickname";

        //given
        Users savedUsers = usersRepository.save(Users.builder()
                .googleUserId(googleUserId)
                .naverUserId("naverUserId")
                .kakaoUserId("kakaoUserId")
                .userNickname(userNickname)
                .userPhoneNumber("userPhoneNumber")
                .build());

        String url = "http://localhost:" + port + "/api/v1/users/findUser/" + googleUserId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

//        mvc.perform(
//                get("/hello/dto")
//                        .param("name", name)
//                        .param("amount", String.valueOf(amount)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(name)))
//                .andExpect(jsonPath("$.amount", is(amount)));

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getUserNickname()).isEqualTo(userNickname);
    }

}
