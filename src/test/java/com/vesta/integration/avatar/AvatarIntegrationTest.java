package com.vesta.integration.avatar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.common.AvatarUtilData;
import com.vesta.controller.view.AvatarView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.AvatarRepository;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.AvatarEntity;
import com.vesta.repository.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static com.vesta.common.AvatarUtilData.avatarEntityWithUser;
import static com.vesta.common.UserUtilData.userEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AvatarIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvatarRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void deleteByIdSuccess() throws Exception {

        AvatarEntity entity = repository.save(AvatarUtilData.avatarEntity());

        this.mvc.perform(delete("/avatar/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addSubjectSuccess() throws Exception {

        UserEntity entity = userRepository.save(userEntity());

        MockMultipartFile fstmp = getMockMultipartFile();

        this.mvc.perform(multipart("/avatar/{userId}", entity.getId())
                .file(fstmp)
                .contentType(MediaType.ALL)
                .accept(MediaType.ALL))
                .andExpect(status().isCreated());
    }

    @WithMockUser
    @Test
    public void addSubjectInsucces_UserNotFound() throws Exception {

        MockMultipartFile fstmp = getMockMultipartFile();

        this.mvc.perform(multipart("/avatar/{userId}", 1L)
                .file(fstmp)
                .contentType(MediaType.ALL)
                .accept(MediaType.ALL))
                .andExpect(status().isNotFound());

    }

    @WithMockUser
    @Test
    public void test_AvatarByUserId_Valid() throws Exception {

        UserEntity userEntity = userRepository.save(userEntity());
        AvatarEntity entity = repository.save(avatarEntityWithUser(userEntity));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/avatar/user/{userId}/avatar", userEntity.getId())
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.ALL))
                .andReturn();

        assertNotNull(mvcResult.getResponse());
        assertThat(mvcResult.getResponse().getContentAsByteArray(), is(entity.getAvatar()));

    }

    @WithMockUser
    @Test
    public void test_AvatarByUserId_UserNotFound() throws Exception {

        this.mvc.perform(get("/avatar/user/{userId}/avatar", 1L)
                .contentType(MediaType.ALL)
                .accept(MediaType.ALL))
                .andExpect(status().isNotFound());
    }

    @WithMockUser
    @Test
    public void test_getByUserId_Valid() throws Exception {

        UserEntity userEntity = userRepository.save(userEntity());
        AvatarEntity entity = repository.save(avatarEntityWithUser(userEntity));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/avatar/user/{userId}", userEntity.getId())
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.ALL))
                .andReturn();

        AvatarView response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), AvatarView.class);

        assertNotNull(response);
        assertThat(response.getId(), is(entity.getId()));
        assertThat(response.getAvatar(), is(entity.getAvatar()));
        assertThat(response.getName(), is(entity.getName()));
    }

    private MockMultipartFile getMockMultipartFile() throws IOException {
        byte[] b = new byte[20];
        new Random().nextBytes(b);
        InputStream file = new ByteArrayInputStream(b);

        return new MockMultipartFile("image", AvatarUtilData.AVATAR_NAME, "multipart/form-data", file);
    }
}
