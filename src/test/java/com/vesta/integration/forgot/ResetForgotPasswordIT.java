package com.vesta.integration.forgot;

import com.google.gson.Gson;
import com.vesta.controller.view.UserResetForgotView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.TokenService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.vesta.integration.common.UtilIntegration.createUserEntiyWithPassword;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResetForgotPasswordIT extends IntegrationConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void submitPasswordConflict() throws Exception {
        UserEntity userEntity = createUserEntiyWithPassword(passwordEncoder.encode("password"));
        userRepository.save(userEntity);

        UserResetForgotView userResetForgotView = new UserResetForgotView();

        userResetForgotView.setPassword("password");
        userResetForgotView.setToken(tokenService.generatedEmailToken("username").getToken());

        Gson gson = new Gson();
        String json = gson.toJson(userResetForgotView);

        this.mvc.perform(post("/user/reset/forgot/password")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void submitPasswordSucces() throws Exception {
        UserEntity userEntity = createUserEntiyWithPassword(passwordEncoder.encode("password"));
        userRepository.save(userEntity);

        UserResetForgotView userResetForgotView = new UserResetForgotView();

        userResetForgotView.setPassword("newPassword");
        userResetForgotView.setToken(tokenService.generatedEmailToken("username").getToken());

        Gson gson = new Gson();
        String json = gson.toJson(userResetForgotView);

        this.mvc.perform(post("/user/reset/forgot/password")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void accesResetPasswordWithInvalidToken() throws Exception {
        UserResetForgotView userResetForgotView = new UserResetForgotView();

        userResetForgotView.setPassword("newPassword");
        userResetForgotView.setToken(tokenService.generatedEmailToken("username").getToken());

        Gson gson = new Gson();
        String json = gson.toJson(userResetForgotView);

        this.mvc.perform(post("/user/reset/forgot/password")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isUnauthorized());
    }
}
