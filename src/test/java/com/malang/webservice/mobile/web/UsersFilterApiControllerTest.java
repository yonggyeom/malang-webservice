package com.malang.webservice.mobile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malang.webservice.mobile.domain.users.Users;
import com.malang.webservice.mobile.domain.users_filter.UsersFilter;
import com.malang.webservice.mobile.domain.users_filter.UsersFilterRepository;
import com.malang.webservice.mobile.web.dto.UsersFilterSaveRequestDto;
import com.malang.webservice.mobile.web.dto.UsersFilterUpdateRequestDto;
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
public class UsersFilterApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersFilterRepository usersFilterRepository;

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
        usersFilterRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void UsersFilter_등록된다() throws Exception {
        String representativeUserId = "representativeUserId";
        String friendType = "friendType";

        //given
        UsersFilterSaveRequestDto requestDto = UsersFilterSaveRequestDto.builder()
                .representativeUserId(representativeUserId)
                .friendType          (friendType)
                .ageFrom             (19)
                .ageTo               (30)
                .build();

        String url = "http://localhost:" + port + "/api/v1/usersFilter";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<UsersFilter> all = usersFilterRepository.findAll();
        assertThat(all.get(0).getFriendType()).isEqualTo(friendType);
    }

    @Test
    @WithMockUser(roles="USER")
    public void UsersFilter_조회된다() throws Exception {
        String representativeUserId = "representativeUserId";
        String friendType = "friendType";

        //given
        UsersFilter savedUsersFilter = usersFilterRepository.save(UsersFilter.builder()
                .representativeUserId(representativeUserId)
                .friendType          (friendType)
                .ageFrom             (19)
                .ageTo               (30)
                .build());

        String url = "http://localhost:" + port + "/api/v1/usersFilter/findUserFilter/" + representativeUserId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<UsersFilter> all = usersFilterRepository.findAll();
        assertThat(all.get(0).getRepresentativeUserId()).isEqualTo(representativeUserId);
    }

    @Test
    @WithMockUser(roles="USER")
    public void UsersFilter_조회된다_ById() throws Exception {
        String representativeUserId = "representativeUserId";
        String friendType = "friendType";

        //given
        UsersFilter savedUsersFilter = usersFilterRepository.save(UsersFilter.builder()
                .representativeUserId(representativeUserId)
                .friendType          (friendType)
                .ageFrom             (19)
                .ageTo               (30)
                .build());

        Long updateId = savedUsersFilter.getId();

        String url = "http://localhost:" + port + "/api/v1/usersFilter/find/" + updateId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<UsersFilter> all = usersFilterRepository.findAll();
        assertThat(all.get(0).getFriendType()).isEqualTo(friendType);
    }

    @Test
    @WithMockUser(roles="USER")
    public void UsersFilter_수정된다() throws Exception {
        //given
        UsersFilter savedUsersFilter = usersFilterRepository.save(UsersFilter.builder()
                .friendType("friendType")
                .build());

        Long updateId = savedUsersFilter.getId();
        String expectedFriendType = "friendType2";

        UsersFilterUpdateRequestDto requestDto = UsersFilterUpdateRequestDto.builder()
                .friendType(expectedFriendType)
                .build();

        String url = "http://localhost:" + port + "/api/v1/usersFilter/update/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<UsersFilter> all = usersFilterRepository.findAll();
        assertThat(all.get(0).getFriendType()).isEqualTo(expectedFriendType);
    }
}
