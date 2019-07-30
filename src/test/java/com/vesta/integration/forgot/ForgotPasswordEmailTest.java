package com.vesta.integration.forgot;

import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.internet.MimeMessage;

import static com.vesta.common.UserUtilData.USER_EMAIL;
import static com.vesta.common.UserUtilData.userEntity;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ForgotPasswordEmailTest extends IntegrationConfigTest {

    private final String URL_TEMPLATE = "/user/forgot/password/email";

    @Rule
    public SmtpServerRuleTemplate mailServerRule = new SmtpServerRuleTemplate("username", "password", 2525);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void submitPasswordForgotSuccess() throws Exception {

        UserEntity userEntity = userEntity();
        userRepository.save(userEntity);

        this.mvc.perform(post(URL_TEMPLATE)
                .with(csrf())
                .param("email", USER_EMAIL))
                .andExpect(status().isOk());

        MimeMessage[] receivedMessages = mailServerRule.getMessages();
        MimeMessage current = receivedMessages[0];

        assertEquals(1, receivedMessages.length);
        assertEquals(USER_EMAIL, current.getAllRecipients()[0].toString());
    }

    @Test
    public void submitPasswordForgotInsucces() throws Exception{

        this.mvc.perform(post(URL_TEMPLATE)
                .with(csrf())
                .param("email", "invalid@gmail.com"))
                .andExpect(status().isOk());
    }
}
