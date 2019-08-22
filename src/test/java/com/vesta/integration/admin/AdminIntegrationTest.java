package com.vesta.integration.admin;

import com.google.gson.Gson;
import com.vesta.common.UserUtilData;
import com.vesta.controller.view.UserCreateView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private UserRepository userRepository;

    @WithMockUser
    @Test
    public void test_update_ById() throws Exception {

        UserEntity entity = userRepository.save(UserUtilData.userEntity());
        UserCreateView view = UserUtilData.subjectUpdateView();

        Gson gson = new Gson();
        String json = gson.toJson(view);

        this.mvc.perform(put("/user/admin/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
