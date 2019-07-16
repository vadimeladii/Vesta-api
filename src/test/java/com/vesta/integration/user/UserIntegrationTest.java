package com.vesta.integration.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

public class UserIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @WithMockUser
    @Test
    public void getListOfUsers() {
    }
}
