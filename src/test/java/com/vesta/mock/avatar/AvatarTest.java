package com.vesta.mock.avatar;

import com.vesta.exception.VestaException;
import com.vesta.repository.AvatarRepository;
import com.vesta.repository.UserRepository;
import com.vesta.service.AvatarService;
import com.vesta.service.converter.AvatarConverter;
import com.vesta.service.impl.AvatarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transactional;

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
}
