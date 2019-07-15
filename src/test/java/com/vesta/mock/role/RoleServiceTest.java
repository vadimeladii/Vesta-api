package com.vesta.mock.role;

import com.vesta.repository.RoleRepository;
import com.vesta.repository.entity.RoleEntity;
import com.vesta.service.RolesService;
import com.vesta.service.impl.RolesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RoleServiceTest {

    private RolesService rolesService;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        rolesService = new RolesServiceImpl(roleRepository);
    }

    @Test
    public void test_findByRoleName_validRoleName() {
        // given
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("test");

        Mockito.when(roleRepository.findByName(roleEntity.getName()))
                .thenReturn(Optional.of(roleEntity));

        // when
        RoleEntity returnEntity = rolesService.findByName(roleEntity.getName());

        // then
        assertNotNull(returnEntity);
        assertThat(returnEntity.getId(), is(returnEntity.getId()));
        assertThat(returnEntity.getName(), is(returnEntity.getName()));
        verify(roleRepository).findByName(roleEntity.getName());
    }
}
