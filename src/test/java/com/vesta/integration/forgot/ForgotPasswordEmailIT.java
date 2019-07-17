package com.vesta.integration.forgot;

import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.internet.MimeMessage;

import static com.vesta.integration.common.UtilIntegration.createUserEntiy;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ForgotPasswordEmailIT extends IntegrationConfigTest {

    @Rule
    public SmtpServerRuleTemplateTest mailServerRule = new SmtpServerRuleTemplateTest(2525);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void submitPassfordForgotSucces() throws Exception {

        UserEntity userEntity = createUserEntiy();
        userRepository.save(userEntity);

        this.mvc.perform(post("/user/forgot/password/email")
                .with(csrf())
                .param("email", "vesta.api@gmail.com"))
                .andExpect(status().isOk());

        MimeMessage[] receivedMessages = mailServerRule.getMessages();
        assertEquals(1, receivedMessages.length);
        MimeMessage current = receivedMessages[0];
        assertEquals("vesta.api@gmail.com", current.getAllRecipients()[0].toString());
    }
}
