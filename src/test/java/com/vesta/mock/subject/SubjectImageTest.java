package com.vesta.mock.subject;

import com.vesta.common.SubjectImageUtilData;
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

import java.util.List;
import java.util.Optional;

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
        SubjectImageEntity subjectImageEntity = SubjectImageUtilData.subjectImageEntity();

        // when
        Mockito.when(repository.findById(subjectImageEntity.getId()))
                .thenReturn(Optional.of(subjectImageEntity));

        // then
        service.getById(subjectImageEntity.getId());
        verify(repository).findById(subjectImageEntity.getId());
    }

    @Test(expected = VestaException.class)
    public void getById_Invalid() {
        service.getById(null);
    }

    @Test
    public void deleteById_Succes() {
        // given
        SubjectImageEntity subjectImageEntity = SubjectImageUtilData.subjectImageEntity();
        // when
        service.deleteImage(subjectImageEntity.getId());
        // then
        verify(repository).deleteById(subjectImageEntity.getId());
    }

    @Test
    public void addImage_Success() {
        // given
        SubjectImageDto subjectImageDto = new SubjectImageDto();
        subjectImageDto.setImage("test-image");

        service.createImage(subjectImageDto);
    }

    @Test
    public void findAll_Images() {
        // given
        SubjectImageEntity subjectImageEntity = SubjectImageUtilData.subjectImageEntity();
        // when
        Mockito.when(repository.findAll())
                .thenReturn(List.of(subjectImageEntity));
        service.getAll();
    }
}
