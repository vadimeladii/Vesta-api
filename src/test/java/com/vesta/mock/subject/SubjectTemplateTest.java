package com.vesta.mock.subject;

import com.vesta.common.SubjectTemplateUtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectTemplateEntity;
import com.vesta.service.SubjectTemplateService;
import com.vesta.service.converter.SubjectTemplateConverter;
import com.vesta.service.dto.SubjectTemplateDto;
import com.vesta.service.impl.SubjectTemplateServiceImpl;
import org.junit.Assert;
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
public class SubjectTemplateTest {

    private SubjectTemplateService service;

    @Mock
    private SubjectTemplateRepository repository;

    private SubjectTemplateConverter converter = new SubjectTemplateConverter();

    @Before
    public void setUp() {
        service = new SubjectTemplateServiceImpl(repository, converter);
    }

    @Test
    public void getById_Valid() {
        // given
        SubjectTemplateEntity subjectTemplateEntity = SubjectTemplateUtilData.subjectTemplateEntity();

        // when
        Mockito.when(repository.findById(subjectTemplateEntity.getId()))
                .thenReturn(Optional.of(subjectTemplateEntity));

        // then
        service.getById(subjectTemplateEntity.getId());
        verify(repository).findById(subjectTemplateEntity.getId());
    }

    @Test(expected = VestaException.class)
    public void getById_Invalid() {
        service.getById(null);
    }

    @Test
    public void deleteById_Succes() {
        // given
        SubjectTemplateEntity subjectTemplateEntity = SubjectTemplateUtilData.subjectTemplateEntity();
        // when
        service.delete(subjectTemplateEntity.getId());
        // then
        verify(repository).deleteById(subjectTemplateEntity.getId());
    }

    @Test
    public void addImage_Success() {
        // given
        SubjectTemplateDto subjectTemplateDto = new SubjectTemplateDto();
        subjectTemplateDto.setImage("test-image");

        service.create(subjectTemplateDto);
    }

    @Test
    public void findAll_Images() {
        // given
        SubjectTemplateEntity subjectTemplateEntity = SubjectTemplateUtilData.subjectTemplateEntity();
        // when
        Mockito.when(repository.findAll())
                .thenReturn(List.of(subjectTemplateEntity));
        List<SubjectTemplateDto> subjectTemplates = service.getAll();

        Assert.assertEquals(subjectTemplates.size(), 1);
        Assert.assertEquals(subjectTemplates.get(0).getImage(), subjectTemplateEntity.getImage());
    }
}