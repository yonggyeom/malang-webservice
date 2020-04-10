package com.malang.webservice.mobile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malang.webservice.mobile.domain.chats.Chats;
import com.malang.webservice.mobile.domain.chats.ChatsRepository;
import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.domain.messages.MessagesRepository;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.web.dto.ChatsSaveRequestDto;
import com.malang.webservice.mobile.web.dto.MessagesSaveRequestDto;
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
public class MessagesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MessagesRepository messagesRepository;

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
        messagesRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Messages_등록된다() throws Exception {
        String fromUserId = "fromUserId";

        //given
        MessagesSaveRequestDto requestDto = MessagesSaveRequestDto.builder()
                .chatId("chatId")
                .fromUserId(fromUserId)
                .text("text")
                .build();

        String url = "http://localhost:" + port + "/api/v1/messages";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Messages> all = messagesRepository.findAll();
        assertThat(all.get(0).getFromUserId()).isEqualTo(fromUserId);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Messages_전체조회된다() throws Exception {
        String text = "text";

        //given
        Messages savedMessages = messagesRepository.save(Messages.builder()
                .chatId("chatId")
                .fromUserId("fromUserId")
                .text(text)
                .build());

        String getChatId = savedMessages.getChatId();

        String url = "http://localhost:" + port + "/api/v1/messages/findAllMyMessages/" + getChatId + "/0";

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<Messages> all = messagesRepository.findAll();
        assertThat(all.get(0).getText()).isEqualTo(text);
    }
}
