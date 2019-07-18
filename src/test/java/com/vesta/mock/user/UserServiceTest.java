package com.vesta.mock.user;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, userConverter, tokenService, passwordEncoder, rolesService, emailService);
    }

    @Test
    public void test_findByName_validUserName() {

        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("firstname");
        userEntity.setLastName("lastname");
        userEntity.setUsername("username");
        userEntity.setEmail("test2@gmail.com");
        userEntity.setRoles(Collections.emptyList());

        Mockito.when(userRepository.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.of(userEntity));

        // when
        UserDto returnDto = userService.getByUsername(userEntity.getUsername());

        // then
        assertNotNull(returnDto);
        assertThat(userEntity.getFirstName(), is(returnDto.getFirstName()));
        assertThat(userEntity.getLastName(), is(returnDto.getLastName()));
        assertThat(userEntity.getUsername(), is(returnDto.getUsername()));
        assertThat(userEntity.getEmail(), is(returnDto.getEmail()));
        verify(userRepository).findByUsername(userEntity.getUsername());
    }

    @Test
    public void test_findByName_invalidUserName() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("firstname");
        userEntity.setLastName("lastname");
        userEntity.setUsername("username");
        userEntity.setEmail("test2@gmail.com");
        userEntity.setRoles(Collections.emptyList());

        Mockito.when(userRepository.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.of(userEntity));

        // when
        Assertions.assertThrows(VestaException.class, () -> userService.getByUsername("invalidName"));
    }

    @Test
    public void test_findById_validId(){

        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("firstname");
        userEntity.setLastName("lastname");
        userEntity.setUsername("username");
        userEntity.setEmail("test2@gmail.com");
        userEntity.setRoles(Collections.emptyList());

        Mockito.when(userRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));

        // when
        UserDto returnDto = userService.getById(userEntity.getId());

        // then
        assertNotNull(returnDto);
        assertThat(userEntity.getId(), is(returnDto.getId()));
        assertThat(userEntity.getFirstName(), is(returnDto.getFirstName()));
        assertThat(userEntity.getLastName(), is(returnDto.getLastName()));
        assertThat(userEntity.getUsername(), is(returnDto.getUsername()));
        assertThat(userEntity.getEmail(), is(returnDto.getEmail()));
        verify(userRepository).findById(userEntity.getId());
    }

    @Test
    public void test_findById_invalidId() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1l);
        userEntity.setFirstName("firstname");
        userEntity.setLastName("lastname");
        userEntity.setUsername("username");
        userEntity.setEmail("test2@gmail.com");
        userEntity.setRoles(Collections.emptyList());

        Mockito.when(userRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));

        // when
        Assertions.assertThrows(VestaException.class, () -> userService.getById(null));
    }


}
