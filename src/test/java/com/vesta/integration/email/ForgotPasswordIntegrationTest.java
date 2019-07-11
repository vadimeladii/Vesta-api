package com.vesta.integration.email;

import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.GreenMail;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ForgotPasswordIntegrationTest extends IntegrationConfigTest {

    @Rule
    public SmtpServerRuleTemplateTest smtpServerRule = new SmtpServerRuleTemplateTest(2525);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void submitPassfordForgotSucces() throws Exception {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("username");
        userEntity.setEmail("test.mail@gmai.com");
        userEntity.setPassword("password");
        userEntity.setLastName("ForTest");
        userEntity.setFirstName("ForTest1");

        userRepository.save(userEntity);

        // when
        this.mvc.perform(post("/user/forgot/password/email")
                .with(csrf())
                .param("email", "test.mail@gmai.com"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forgot-password?success"));

        MimeMessage[] receivedMessages = smtpServerRule.getMessages();
        assertEquals(1, receivedMessages.length);
        MimeMessage current = receivedMessages[0];
        assertEquals("vesta.api@gmail.com", current.getAllRecipients()[0].toString());
    }

//    @Test
//    public void submitPassfordForgotInvalidEmail() throws Exception {
//        // given
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(1L);
//        userEntity.setUsername("username");
//        userEntity.setEmail("test.mail@gmai.com");
//        userEntity.setPassword("password");
//        userEntity.setLastName("ForTest");
//        userEntity.setFirstName("ForTest1");
//
//        userRepository.save(userEntity);
//
//        // when
//        this.mvc.perform(post("/user/forgot/password/email")
//                .with(csrf())
//                .param("email", "invalid.mail@gmai.com"))
//                .andExpect(model().hasErrors())
//                .andExpect(model().attributeHasFieldErrors("forgotPasswordEmailForm", "email"))
//                .andExpect(status().isOk());
//
//    }
}
