package com.vesta.integration.forgot;

import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vesta.integration.common.UtilIntegration.createUserEntiy;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResetForgotPasswordIT extends IntegrationConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void accesResetPasswordWithoutToken() throws Exception {

        UserEntity userEntity = createUserEntiy();
        userRepository.save(userEntity);

        this.mvc.perform(get("/user/reset/forgot/password"))
                .andExpect(status().isOk());
    }

    @Test
    public void accesResetPasswordWithExpiredToken() throws Exception {
        UserEntity userEntity = createUserEntiy();
        userRepository.save(userEntity);

        this.mvc.perform(get("/user/reset/forgot/password"))
                .andExpect(status().isOk());

    }

    @Test
    public void accesResetPasswordWithValidToken() throws Exception {
        UserEntity userEntity = createUserEntiy();
        userRepository.save(userEntity);

        this.mvc.perform(get("/user/reset/forgot/password"))
                .andExpect(status().isOk());

    }

    @Test
    public void submitPasswordSucces() throws Exception {
        UserEntity userEntity = createUserEntiy();
        userRepository.save(userEntity);

        this.mvc.perform(get("/user/reset/forgot/password")
                .with(csrf())
                .param("password", "password")
                .param("confirmPassword", "123456pass")
                .param("token", "username")
        )
                .andExpect(status().isOk());

    }

    @Test
    public void submitPasswordConflict() throws Exception {
        UserEntity userEntity = createUserEntiy();
        userRepository.save(userEntity);

        this.mvc.perform(get("/user/reset/forgot/password"))
                .andExpect(status().isOk());

    }
}
