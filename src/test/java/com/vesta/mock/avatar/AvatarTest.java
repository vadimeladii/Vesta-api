package com.vesta.mock.avatar;

import com.vesta.common.AvatarUtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.AvatarRepository;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.AvatarEntity;
import com.vesta.service.AvatarService;
import com.vesta.service.converter.AvatarConverter;
import com.vesta.service.dto.AvatarDto;
import com.vesta.service.impl.AvatarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class AvatarTest {

    private AvatarService service;

    @Mock
    private AvatarRepository repository;

    @Mock
    private UserRepository userRepository;

    private AvatarConverter converter = new AvatarConverter();

    @Before
    public void setUp() {
        service = new AvatarServiceImpl(repository, userRepository, converter);
    }

    @Test(expected = VestaException.class)
    public void test_findByUserId_invalidId() {
        service.getByUserId(null);
    }

    @Test
    public void test_findByUserId_valid() {
        // given
        AvatarEntity entity = AvatarUtilData.avatarEntity();

        // when
        Mockito.when(repository.findByUserEntity(entity.getUserEntity().getId()))
                .thenReturn(Optional.of(entity));

        // then
        AvatarDto returnDto = service.getByUserId(entity.getUserEntity().getId());

        assertNotNull(returnDto);
        assertThat(entity.getId(), is(returnDto.getId()));
        assertThat(entity.getAvatar(), is(returnDto.getAvatar()));
        assertThat(entity.getName(), is(returnDto.getName()));
        verify(repository).findByUserEntity(entity.getUserEntity().getId());
    }

    @Test(expected = VestaException.class)
    public void test_AvatarByUserId_Invalid() {
        service.getAvatarByUserId(null);
    }
}
