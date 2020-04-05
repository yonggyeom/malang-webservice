package com.malang.webservice.mobile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malang.webservice.mobile.domain.chats.Chats;
import com.malang.webservice.mobile.domain.chats.ChatsRepository;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImages;
import com.malang.webservice.mobile.domain.users_profile_images.UsersProfileImagesRepository;
import com.malang.webservice.mobile.web.dto.*;
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
public class ChatsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ChatsRepository chatsRepository;

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
        chatsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Chats_등록된다() throws Exception {
        String senderUserId = "senderUserId";

        //given
        ChatsSaveRequestDto requestDto = ChatsSaveRequestDto.builder()
                .chatId("chatId")
                .senderUserId(senderUserId)
                .receiverUserId("receiverUserId")
                .approvalYn(0)
                .build();

        String url = "http://localhost:" + port + "/api/v1/chats";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Chats> all = chatsRepository.findAll();
        assertThat(all.get(0).getSenderUserId()).isEqualTo(senderUserId);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Chats_조회된다() throws Exception {
        String senderUserId = "senderUserId";

        //given
        Chats savedChats = chatsRepository.save(Chats.builder()
                .chatId("chatId")
                .senderUserId(senderUserId)
                .receiverUserId("receiverUserId")
                .approvalYn(0)
                .build());

        String getChatId = savedChats.getChatId();

        String url = "http://localhost:" + port + "/api/v1/chats/findChat/" + getChatId + "/" + senderUserId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<Chats> all = chatsRepository.findAll();
        assertThat(all.get(0).getSenderUserId()).isEqualTo(senderUserId);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Chats_수정된다() throws Exception {
        //given
        Chats savedChats = chatsRepository.save(Chats.builder()
                .chatId("chatId")
                .build());

        Long updateId = savedChats.getId();
        String expectedChatId = "chatId2";

        ChatsUpdateRequestDto requestDto = ChatsUpdateRequestDto.builder()
                .chatId(expectedChatId)
                .build();

        String url = "http://localhost:" + port + "/api/v1/chats/update/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Chats> all = chatsRepository.findAll();
        assertThat(all.get(0).getChatId()).isEqualTo(expectedChatId);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Chats_전체조회된다() throws Exception {
        String senderUserId = "senderUserId";

        //given
        Chats savedChats = chatsRepository.save(Chats.builder()
                .chatId("chatId")
                .senderUserId(senderUserId)
                .receiverUserId("receiverUserId")
                .approvalYn(0)
                .build());

        String getSenderUserId = savedChats.getSenderUserId();

        String url = "http://localhost:" + port + "/api/v1/chats/findAllChatForMe/" + getSenderUserId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<Chats> all = chatsRepository.findAll();
        assertThat(all.get(0).getSenderUserId()).isEqualTo(senderUserId);
    }

}
