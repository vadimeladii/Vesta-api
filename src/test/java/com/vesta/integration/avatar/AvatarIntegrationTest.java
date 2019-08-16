package com.vesta.integration.avatar;

import com.vesta.common.AvatarUtilData;
import com.vesta.common.UserUtilData;
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
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AvatarIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvatarRepository repository;

    @Test
    public void deleteByIdSuccess() throws Exception {

        AvatarEntity entity = AvatarUtilData.avatarEntity();
        repository.save(entity);

        this.mvc.perform(delete("/avatar/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addSubjectSuccess() throws Exception {

        UserEntity entity = UserUtilData.userEntity();
        userRepository.save(entity);

        MockMultipartFile fstmp = getMockMultipartFile();

        this.mvc.perform(multipart("/avatar/{userId}", entity.getId())
                .file(fstmp)
                .contentType(MediaType.ALL)
                .accept(MediaType.ALL))
                .andExpect(status().isOk());

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

    private MockMultipartFile getMockMultipartFile() throws IOException {
        byte[] b = new byte[20];
        new Random().nextBytes(b);
        InputStream file = new ByteArrayInputStream(b);

        return new MockMultipartFile("image", AvatarUtilData.AVATAR_NAME , "multipart/form-data", file);
    }
}
