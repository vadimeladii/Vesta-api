package com.vesta.mock.role;

import com.vesta.common.RoleUtilData;
import com.vesta.repository.RoleRepository;
import com.vesta.repository.entity.RoleEntity;
import com.vesta.service.RolesService;
import com.vesta.service.impl.RolesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    private RolesService rolesService;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        rolesService = new RolesServiceImpl(roleRepository);
    }

    @Test
    public void test_findByRoleName_validRoleName() {
        // given
        RoleEntity roleEntity = RoleUtilData.roleEntity();

        // when
        Mockito.when(roleRepository.findByName(roleEntity.getName()))
                .thenReturn(Optional.of(roleEntity));

        // then
        RoleEntity returnEntity = rolesService.findByName(roleEntity.getName());

        assertNotNull(returnEntity);
        assertThat(returnEntity.getId(), is(returnEntity.getId()));
        assertThat(returnEntity.getName(), is(returnEntity.getName()));
        verify(roleRepository).findByName(roleEntity.getName());
    }
}
