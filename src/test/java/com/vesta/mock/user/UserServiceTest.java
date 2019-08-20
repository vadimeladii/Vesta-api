package com.vesta.mock.user;

import com.vesta.common.RoleUtilData;
import com.vesta.common.UserUtilData;
import com.vesta.controller.view.Token;
import com.vesta.exception.ConflictException;
import com.vesta.exception.NotFoundException;
import com.vesta.exception.UnauthorizedException;
import com.vesta.exception.VestaException;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.EmailService;
import com.vesta.service.RolesService;
import com.vesta.service.TokenService;
import com.vesta.service.UserService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import com.vesta.service.impl.UserServiceImpl;
import com.vesta.service.dto.Roles;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
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
    private RolesService rolesService;

    @Mock
    private EmailService emailService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserConverter userConverter = new UserConverter();

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, userConverter, tokenService, passwordEncoder, rolesService, emailService);
    }

    @Test
    public void test_findByName_validUserName() {
        // given
        UserEntity userEntity = UserUtilData.userEntity();

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
    public void test_findById_validId() {
        // given
        UserEntity userEntity = UserUtilData.userEntity();

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

    @Test
    public void test_findAll_valid() {
        // given
        UserEntity userEntity1 = UserUtilData.userEntity();
        UserEntity userEntity2 = UserUtilData.userEntity();

        // when
        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(userEntity1, userEntity2));

        // then
        List<UserDto> users = userService.findAll();

        assertEquals(users.size(), 2);
        assertNotNull(users.get(0));
        assertNotNull(users.get(1));
        assertThat(userEntity1.getId(), is(users.get(0).getId()));
        assertThat(userEntity1.getFirstName(), is(users.get(0).getFirstName()));
        assertThat(userEntity1.getLastName(), is(users.get(0).getLastName()));
        assertThat(userEntity1.getUsername(), is(users.get(0).getUsername()));
        assertThat(userEntity1.getEmail(), is(users.get(0).getEmail()));
    }

    @Test(expected = ConflictException.class)
    public void test_create_invalidUsername() {
        // given
        UserDto userDto = UserUtilData.userDto();

        // when
        Mockito.when(userRepository.existsByUsername(userDto.getUsername()))
                .thenReturn(Boolean.TRUE);

        // then
        userService.create(userDto);
    }

    @Test(expected = ConflictException.class)
    public void test_create_invalidEmail() {
        // given
        UserDto userDto = UserUtilData.userDto();

        // when
        Mockito.when(userRepository.existsByUsername(userDto.getUsername()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(userRepository.existsByEmail(userDto.getEmail()))
                .thenReturn(Boolean.TRUE);
        // then
        userService.create(userDto);
    }

    @Test
    public void test_create_valid() {
        // given
        UserDto userDto = UserUtilData.userDto();

        // when
        Mockito.when(userRepository.existsByUsername(userDto.getUsername()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(userRepository.existsByEmail(userDto.getEmail()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(rolesService.findByName(Roles.USER.name()))
                .thenReturn(RoleUtilData.roleEntity());

        // then
        userService.create(userDto);
    }

    @Test(expected = NotFoundException.class)
    public void test_update_invalidId() {
        // given
        UserDto userDto = UserUtilData.userDto();

        // when
        Mockito.when(userRepository.findById(userDto.getId()))
                .thenReturn(Optional.empty());

        // then
        userService.update(userDto.getId(), userDto);
    }

    @Test
    public void test_update_valid() {
        // given
        UserDto userDto = UserUtilData.userDto();

        // when
        Mockito.when(userRepository.findById(userDto.getId()))
                .thenReturn(Optional.of(UserUtilData.userEntity()));

        // then
        userService.update(userDto.getId(), userDto);
    }

    @Test(expected = UnauthorizedException.class)
    public void test_login_invalidUsername() {
        // given
        AccountCredential accountCredential = UserUtilData.accountCredential();

        // when
        Mockito.when(userRepository.findByUsername(accountCredential.getUsername()))
                .thenReturn(Optional.empty());

        // then
        userService.login(accountCredential);
    }

    @Test(expected = UnauthorizedException.class)
    public void test_login_invalidMatchesPasswords() {
        // given
        AccountCredential accountCredential = UserUtilData.accountCredential();
        UserEntity userEntity = UserUtilData.userEntity(passwordEncoder.encode(RandomStringUtils.randomAlphabetic(10)));

        // when
        Mockito.when(userRepository.findByUsername(accountCredential.getUsername()))
                .thenReturn(Optional.of(userEntity));

        // then
        userService.login(accountCredential);
    }

    @Test
    public void test_login_valid() {
        // given
        var accessToken = RandomStringUtils.randomAlphabetic(10);
        var refreshToken = RandomStringUtils.randomAlphabetic(10);
        AccountCredential accountCredential = UserUtilData.accountCredential();
        UserEntity userEntity = UserUtilData.userEntity(passwordEncoder.encode(UserUtilData.USER_PASSWORD));

        // when
        Mockito.when(userRepository.findByUsername(accountCredential.getUsername()))
                .thenReturn(Optional.of(userEntity));

        Mockito.when(tokenService.generatedAccessToken(accountCredential.getUsername()))
                .thenReturn(new Token(accessToken));

        Mockito.when(tokenService.generatedRefreshToken(accountCredential.getUsername()))
                .thenReturn(new Token(refreshToken));

        // then
        Map<String, String> login = userService.login(accountCredential);
        assertEquals(login.get("accessToken"), accessToken);
        assertEquals(login.get("refreshToken"), refreshToken);
    }

    @Test
    public void test_refreshToken_valid() {
        // given
        var accessToken = RandomStringUtils.randomAlphabetic(10);
        var refreshToken = RandomStringUtils.randomAlphabetic(10);
        AccountCredential accountCredential = UserUtilData.accountCredential();
        UserEntity userEntity = UserUtilData.userEntity(passwordEncoder.encode(UserUtilData.USER_PASSWORD));

        // when
        Mockito.when(userRepository.findByUsername(accountCredential.getUsername()))
                .thenReturn(Optional.of(userEntity));

        Mockito.when(tokenService.generatedAccessToken(accountCredential.getUsername()))
                .thenReturn(new Token(accessToken));

        Mockito.when(tokenService.generatedRefreshToken(accountCredential.getUsername()))
                .thenReturn(new Token(refreshToken));

        // then
        Map<String, String> login = userService.login(accountCredential);
        assertEquals(login.get("accessToken"), accessToken);
        assertEquals(login.get("refreshToken"), refreshToken);
    }
}
