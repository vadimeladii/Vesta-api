package com.vesta.integration.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vesta.common.UserUtilData;
import com.vesta.controller.view.UserCreateView;
import com.vesta.controller.view.UserView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void test_getById_Valid() throws Exception {

        UserEntity entity = userRepository.save(UserUtilData.userEntity());

        MvcResult mvcResult = mvc.perform(get("/user/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        UserView response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), UserView.class);

        assertNotNull(response);
        Assert.assertThat(response.getId(), CoreMatchers.is(entity.getId()));
        Assert.assertThat(response.getUsername(), CoreMatchers.is(entity.getUsername()));
        Assert.assertThat(response.getEmail(), CoreMatchers.is(entity.getEmail()));
        Assert.assertThat(response.getFirstName(), CoreMatchers.is(entity.getFirstName()));
        Assert.assertThat(response.getLastName(), CoreMatchers.is(entity.getLastName()));
    }


    @WithMockUser
    @Test
    public void test_getAll() throws Exception {

        UserEntity entity = userRepository.save(UserUtilData.userEntity());

        MvcResult mvcResult = mvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        List<UserView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<UserView>>() {
                });

        assertNotNull(response);
        assertNotNull(response.get(0));
        assertNotNull(response);
        assertNotNull(response);
        Assert.assertThat(response.get(0).getId(), CoreMatchers.is(entity.getId()));
        Assert.assertThat(response.get(0).getUsername(), CoreMatchers.is(entity.getUsername()));
        Assert.assertThat(response.get(0).getEmail(), CoreMatchers.is(entity.getEmail()));
        Assert.assertThat(response.get(0).getFirstName(), CoreMatchers.is(entity.getFirstName()));
        Assert.assertThat(response.get(0).getLastName(), CoreMatchers.is(entity.getLastName()));
    }

    @WithMockUser
    @Test
    public void test_update_ById() throws Exception {

        UserEntity entity = userRepository.save(UserUtilData.userEntity());
        UserCreateView view = UserUtilData.subjectUpdateView();

        Gson gson = new Gson();
        String json = gson.toJson(view);

        this.mvc.perform(put("/user/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
