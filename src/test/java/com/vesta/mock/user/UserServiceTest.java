package com.vesta.mock.user;

import com.vesta.common.UtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.EmailService;
import com.vesta.service.RolesService;
import com.vesta.service.TokenService;
import com.vesta.service.UserService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.UserDto;
import com.vesta.service.impl.UserServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RolesService rolesService;

    @Mock
    private EmailService emailService;

    private UserConverter userConverter = new UserConverter();

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, userConverter, tokenService, passwordEncoder, rolesService, emailService);
    }

    @Test
    public void test_findByName_validUserName() {

        // given
        UserEntity userEntity = UtilData.userEntity();

        // when
        Mockito.when(userRepository.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.of(userEntity));

        // then
        UserDto returnDto = userService.getByUsername(userEntity.getUsername());

        assertNotNull(returnDto);
        assertThat(userEntity.getFirstName(), is(returnDto.getFirstName()));
        assertThat(userEntity.getLastName(), is(returnDto.getLastName()));
        assertThat(userEntity.getUsername(), is(returnDto.getUsername()));
        assertThat(userEntity.getEmail(), is(returnDto.getEmail()));
        verify(userRepository).findByUsername(userEntity.getUsername());
    }

    @Test(expected = VestaException.class)
    public void test_findByName_invalidUserName() {
        userService.getByUsername(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    public void test_findById_validId(){
        // given
        UserEntity userEntity = UtilData.userEntity();

        // when
        Mockito.when(userRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));

        // then
        UserDto returnDto = userService.getById(userEntity.getId());

        assertNotNull(returnDto);
        assertThat(userEntity.getId(), is(returnDto.getId()));
        assertThat(userEntity.getFirstName(), is(returnDto.getFirstName()));
        assertThat(userEntity.getLastName(), is(returnDto.getLastName()));
        assertThat(userEntity.getUsername(), is(returnDto.getUsername()));
        assertThat(userEntity.getEmail(), is(returnDto.getEmail()));
        verify(userRepository).findById(userEntity.getId());
    }

    @Test(expected = VestaException.class)
    public void test_findById_invalidId() {
        userService.getById(null);
    }
}
