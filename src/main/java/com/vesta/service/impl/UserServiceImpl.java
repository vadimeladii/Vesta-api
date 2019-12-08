package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.exception.ConflictException;
import com.vesta.exception.NotFoundException;
import com.vesta.exception.UnauthorizedException;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.RoleEntity;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.EmailService;
import com.vesta.service.RolesService;
import com.vesta.service.TokenService;
import com.vesta.service.UserService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import com.vesta.util.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.vesta.expression.ExpressionAsserts.verify;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final RolesService rolesService;
    private final EmailService emailService;

    @Override
    public UserDto getById(Long id) {
        log.info("method --- getById");

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("The user doesn't exist"));
        return userConverter.convert(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        log.info("method --- findAll");

        return userRepository.findAll()
                .stream()
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void create(UserDto userDto) {
        log.info("method --- create");

        verify(userRepository.existsByUsername(userDto.getUsername()),
                () -> new ConflictException("Username already exists"));
        verify(userRepository.existsByEmail(userDto.getEmail()),
                () -> new ConflictException("Email already exists"));

        UserEntity entity = userConverter.deconvert(userDto);
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        entity.setRoles(List.of(rolesService.findByName(Roles.USER.name())));
        userRepository.save(entity);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        log.info("method --- update");

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("The user doesn't exist"));

        UserEntity userUpdated = userConverter.deconvert(userDto);
        userEntity.setFirstName(userUpdated.getFirstName());
        userEntity.setLastName(userUpdated.getLastName());
        return userConverter.convert(userRepository.save(userEntity));
    }

    @Override
    public void forgotPasswordMail(String email) {
        log.info("method --- forgotPasswordMail");

        userRepository.findByEmail(email).ifPresentOrElse(userEntity
                        -> emailService.sendEmailForgotPassword(userEntity.getUsername(), userEntity.getEmail()),
                () -> log.error("Can't sent forgot password mail because user doesn't exist: email - {}", email));
    }

    @Override
    public void resetForgotPassword(String token, String password) {
        log.info("method --- resetForgotPassword");

        List<String> username = tokenService.getPayload(token).get("user");
        if(username.size() > 1)
            throw new ConflictException("Only one user can make request");

        UserEntity userEntity = userRepository.findByUsername(username.get(0))
                .orElseThrow(() -> new UnauthorizedException("The username not found"));

        verify(passwordEncoder.matches(password, userEntity.getPassword()),
                () -> new ConflictException("New Password do not must match with Old Password"));
        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
    }

    @Override
    public void delete(Long id) {
        log.info("method --- delete");

        userRepository.deleteById(id);
    }

    @Override
    public UserDto getByUsername(String username) {
        log.info("method --- getByUsername");

        return userConverter.convert(userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("The username doesn't correct")));
    }

    @Override
    public Map<String, String> login(AccountCredential accountCredential) {
        log.info("method --- login");

        UserEntity userEntity = userRepository.findByUsername(accountCredential.getUsername())
                .orElseThrow(() -> new UnauthorizedException("The username doesn't correct"));

        verify(!passwordEncoder.matches(accountCredential.getPassword(), userEntity.getPassword()),
                () -> new UnauthorizedException("The password doesn't correct"));

        Map<String, String> tokens = new HashMap<>();

        tokens.put("accessToken", tokenService.generateAccessToken(accountCredential.getUsername(),
                convertRolesToString((List<RoleEntity>) userEntity.getRoles())).getJwtToken());
        tokens.put("refreshToken", tokenService.generateRefreshToken(accountCredential.getUsername(),
                convertRolesToString((List<RoleEntity>) userEntity.getRoles())).getJwtToken());
        return tokens;
    }

    @Override
    public Token refreshToken(String refreshToken) {
        log.info("method --- refreshToken");

        List<String> username = tokenService.getRefreshPayload(refreshToken).get("user");
        if(username.size() > 1)
            throw new ConflictException("Only one user can be authentificated");

        List<String> roles = tokenService.getRefreshPayload(refreshToken).get("roles");
        return tokenService.generateAccessToken(username.get(0), roles);
    }

    @Override
    public List<String> convertRolesToString(List<RoleEntity> roles) {
        List<String> rolesAsStrings = new ArrayList<>();
        for(int i = 0; i < roles.size(); i++)
            rolesAsStrings.add(roles.get(i).getName());

        return rolesAsStrings;
    }
}
