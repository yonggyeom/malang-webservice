package com.malang.webservice.mobile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malang.webservice.mobile.domain.posts.Posts;
import com.malang.webservice.mobile.domain.posts.PostsRepository;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users.UsersRepository;
import com.malang.webservice.mobile.web.dto.PostsSaveRequestDto;
import com.malang.webservice.mobile.web.dto.PostsUpdateRequestDto;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    public void Users_등록된다() throws Exception {
        String googleUserId = "googleUserId";
        String userNickname = "userNickname";

        //given
        UsersSaveRequestDto requestDto = UsersSaveRequestDto.builder()
                .googleUserId(googleUserId)
                .naverUserId("naverUserId")
                .kakaoUserId("kakaoUserId")
                .userNickname(userNickname)
                .userPhoneNumber("userPhoneNumber")
                .build();

        String url = "http://localhost:" + port + "/api/v1/users";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getUserNickname()).isEqualTo(userNickname);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Users_수정된다() throws Exception {
        //given
        Users savedUsers = usersRepository.save(Users.builder()
                .googleUserId("googleUserId")
                .build());

        Long updateId = savedUsers.getId();
        String expectedGoogleUserId = "googleUserId2";

        UsersUpdateRequestDto requestDto = UsersUpdateRequestDto.builder()
                .googleUserId(expectedGoogleUserId)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users/update/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getGoogleUserId()).isEqualTo(expectedGoogleUserId);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Users_점수수정된다() throws Exception {
        int acquiredScore = 0;
        int evaluatorCnt = 0;

        //given
        Users savedUsers = usersRepository.save(Users.builder()
                .googleUserId("googleUserId")
                .acquiredScore(acquiredScore)
                .evaluatorCnt(evaluatorCnt)
                .build());

        Long updateId = savedUsers.getId();

        String url = "http://localhost:" + port + "/api/v1/users/updateScore/" + updateId + "/1";

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto))
                )
                .andExpect(status().isOk());

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getAcquiredScore()).isEqualTo(acquiredScore + 1);
        assertThat(all.get(0).getEvaluatorCnt()).isEqualTo(evaluatorCnt + 1);
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

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getUserNickname()).isEqualTo(userNickname);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Users_조회된다_ById() throws Exception {
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

        Long updateId = savedUsers.getId();

        String url = "http://localhost:" + port + "/api/v1/users/find/" + updateId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getUserNickname()).isEqualTo(userNickname);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Users_전체조회된다() throws Exception {
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

        Long getId = savedUsers.getId();

        String url = "http://localhost:" + port + "/api/v1/users/findAllUserExceptMe/" + getId;

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

    @Test
    @WithMockUser(roles="USER")
    public void Users_추천신청여부로전체조회된다() throws Exception {
        int reqRecommendationYn = 1;
        String userNickname = "userNickname";

        //given
        Users savedUsers = usersRepository.save(Users.builder()
                .googleUserId("googleUserId")
                .naverUserId("naverUserId")
                .kakaoUserId("kakaoUserId")
                .userNickname(userNickname)
                .userPhoneNumber("userPhoneNumber")
                .reqRecommendationYn(reqRecommendationYn)
                .build());

        String url = "http://localhost:" + port + "/api/v1/users/findAllUserByReqRecommendationYn/" + reqRecommendationYn;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getUserNickname()).isEqualTo(userNickname);
    }

}
