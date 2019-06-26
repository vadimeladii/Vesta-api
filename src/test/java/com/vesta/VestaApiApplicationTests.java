package com.vesta;

import com.vesta.service.UserService;
import com.vesta.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class VestaApiApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Test
    public void setPasswordEncoder() {
        System.out.println(passwordEncoder.encode("test1"));

    }

    @Test
    public void testGetById() {
    }

    @Test
    public void contextLoads() {


    }

}
