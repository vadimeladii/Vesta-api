package com.vesta.integration.user;

import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private UserRepository userRepository;
}
