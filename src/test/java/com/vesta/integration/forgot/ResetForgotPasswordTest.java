package com.vesta.integration.forgot;

import com.google.gson.Gson;
import com.vesta.controller.view.UserResetForgotView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.common.UserUtilData;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.TokenService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.vesta.common.UserUtilData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResetForgotPasswordTest extends IntegrationConfigTest {

    private final String URL_TEMPLATE = "/user/reset/forgot/password";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void submitPasswordConflict() throws Exception {
        UserEntity userEntity = UserUtilData.userEntity(passwordEncoder.encode(USER_PASSWORD));
        userRepository.save(userEntity);

        UserResetForgotView userResetForgotView = new UserResetForgotView();

        userResetForgotView.setPassword(USER_PASSWORD);
        userResetForgotView.setToken(tokenService.generatedEmailToken(USER_USERNAME).getJwtToken());

        Gson gson = new Gson();
        String json = gson.toJson(userResetForgotView);

        this.mvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void submitPasswordSuccess() throws Exception {
        UserEntity userEntity = UserUtilData.userEntity(passwordEncoder.encode(USER_USERNAME));
        userRepository.save(userEntity);

        UserResetForgotView userResetForgotView = new UserResetForgotView();

        userResetForgotView.setPassword(USER_NEW_PASSWORD);
        userResetForgotView.setToken(tokenService.generatedEmailToken(USER_USERNAME).getJwtToken());

        Gson gson = new Gson();
        String json = gson.toJson(userResetForgotView);

        this.mvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void accessResetPasswordWithInvalidToken() throws Exception {
        UserResetForgotView userResetForgotView = new UserResetForgotView();

        userResetForgotView.setPassword(USER_NEW_PASSWORD);
        userResetForgotView.setToken(tokenService.generatedEmailToken(USER_EMAIL).getJwtToken());

        Gson gson = new Gson();
        String json = gson.toJson(userResetForgotView);

        this.mvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isUnauthorized());
    }
}
