package com.vesta.integration.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.controller.view.UserView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @WithMockUser
    @Test
    public void getListOfUsers() throws Exception {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("firstname");
        userEntity.setLastName("lastname");
        userEntity.setUsername("username");
        userEntity.setEmail("test2@gmail.com");

        userRepository.save(userEntity);

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/user/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        UserView response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                UserView.class);

        // then

    }
}
