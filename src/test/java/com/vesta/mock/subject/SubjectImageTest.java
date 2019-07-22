package com.vesta.mock.subject;

import com.vesta.common.UtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.SubjectImageRepository;
import com.vesta.repository.entity.SubjectImageEntity;
import com.vesta.service.SubjectImageService;
import com.vesta.service.converter.SubjectImageConverter;
import com.vesta.service.dto.SubjectImageDto;
import com.vesta.service.impl.SubjectImageServiceImpl;
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
public class SubjectImageTest {

    @Mock
    private SubjectImageService service;

    @Mock
    private SubjectImageRepository repository;

    @Mock
    private SubjectImageConverter converter;

    @Before
    public void setUp() {
        service = new SubjectImageServiceImpl(repository, converter);
    }

    @Test
    public void getById_Valid() {
        // given
        SubjectImageEntity entity = UtilData.subjectImageEntity();

        // when
        Mockito.when(repository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));

        // then
        SubjectImageDto returnDto = service.getById(entity.getId());

        assertNotNull(returnDto);
        assertThat(entity.getId(), is(returnDto.getId()));
        assertThat(entity.getImage(), is(returnDto.getImage()));
        verify(repository).findById(entity.getId());
    }

    @Test(expected = VestaException.class)
    public void getById_Invalid() {
        service.getById(null);
    }
}
