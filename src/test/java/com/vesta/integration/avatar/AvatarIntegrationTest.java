package com.vesta.integration.avatar;

import com.vesta.common.AvatarUtilData;
import com.vesta.common.UserUtilData;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.AvatarRepository;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.AvatarEntity;
import com.vesta.repository.entity.UserEntity;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;

import static com.vesta.common.AvatarUtilData.AVATAR_ID;
import static com.vesta.common.UserUtilData.USER_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AvatarIntegrationTest extends IntegrationConfigTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AvatarRepository repository;

    @WithMockUser
    @Test
    public void deleteByIdSuccess() throws Exception {

        AvatarEntity entity = AvatarUtilData.avatarEntity();
        repository.save(entity);

        this.mvc.perform(delete("/avatar/{id}", AVATAR_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addSubjectSuccess() throws Exception {

        UserEntity entity = UserUtilData.userEntity();
        userRepository.save(entity);

        File resourceFile = ResourceUtils.getFile("src/test/resources/img/light-bulb.svg");

        FileInputStream fi1 = new FileInputStream(resourceFile);
        MockMultipartFile fstmp = new MockMultipartFile("upload", resourceFile.getName(), "multipart/form-data", fi1);

        this.mvc.perform(multipart("/avatar/{userId}", USER_ID)
                .file(fstmp)
                .contentType(MediaType.ALL)
                .accept(MediaType.ALL))
                .andExpect(status().isCreated());

    }

    @WithMockUser
    @Test
    public void addSubjectInsucces_UserNotFound() throws Exception {

        File resourceFile = ResourceUtils.getFile("src/test/resources/img/light-bulb.svg");

        FileInputStream fi1 = new FileInputStream(resourceFile);
        MockMultipartFile fstmp = new MockMultipartFile("upload", resourceFile.getName(), "multipart/form-data", fi1);

        this.mvc.perform(multipart("/avatar/{userId}", USER_ID)
                .file(fstmp)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
