package com.vesta.mock.admin;

import com.vesta.common.UserUtilData;
import com.vesta.exception.NotFoundException;
import com.vesta.repository.UserRepository;
import com.vesta.service.AdminService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.UserDto;
import com.vesta.service.impl.AdminServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class AdminTest {

    private AdminService adminService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;


    @Before
    public void setUp() {
        adminService = new AdminServiceImpl(userRepository, userConverter) {
        };
    }

    @Test(expected = NotFoundException.class)
    public void test_update_invalidId() {
        // given
        UserDto userDto = UserUtilData.userDto();

        // when
        Mockito.when(userRepository.findById(userDto.getId()))
                .thenReturn(Optional.empty());

        // then
        adminService.update(userDto.getId(), userDto);
    }

    @Test
    public void test_update_valid() {
        // given
        UserDto userDto = UserUtilData.userDto();

        // when
        Mockito.when(userRepository.findById(userDto.getId()))
                .thenReturn(Optional.of(UserUtilData.userEntity()));

        // then
        adminService.update(userDto.getId(), userDto);
    }
}