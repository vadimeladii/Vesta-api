package com.vesta.mock.subject;

import com.vesta.common.SubjectUtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.service.SubjectService;
import com.vesta.service.converter.SubjectConverter;
import com.vesta.service.dto.SubjectDto;
import com.vesta.service.impl.SubjectServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.vesta.common.SubjectUtilData.FLOOR_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class SubjectTest {

    private SubjectService service;

    @Mock
    private SubjectRepository repository;

    @Mock
    private SubjectTemplateRepository templateRepository;

    private SubjectConverter converter = new SubjectConverter();

    @Before
    public void setUp() {
        service = new SubjectServiceImpl(repository, converter, templateRepository);
    }

    @Test
    public void test_findById_validId() {
        // given
        SubjectEntity entity = SubjectUtilData.subjectEntity();

        // when
        Mockito.when(repository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));

        // then
        SubjectDto returnDto = service.getById(entity.getId());

        assertNotNull(returnDto);
        assertThat(entity.getId(), is(returnDto.getId()));
        assertThat(entity.getPositionX(), is(returnDto.getPositionX()));
        assertThat(entity.getPositionY(), is(returnDto.getPositionY()));
        assertThat(entity.getScale(), is(returnDto.getScale()));
        assertThat(entity.getRotation(), is(returnDto.getRotation()));
        assertThat(entity.getEditable(), is(returnDto.getEditable()));
        assertThat(entity.getFloorId(), is(returnDto.getFloorId()));
        assertThat(entity.getSubjectTemplateEntity().getImage(), is(returnDto.getImage()));
        verify(repository).findById(entity.getId());
    }

    @Test(expected = VestaException.class)
    public void getById_Invalid() {
        service.getById(null);
    }

    @Test
    public void test_findAll_valid() {
        // given
        SubjectEntity subjectEntity1 = SubjectUtilData.subjectEntity();
        SubjectEntity subjectEntity2 = SubjectUtilData.subjectEntity();

        // when
        Mockito.when(repository.findAll())
                .thenReturn(List.of(subjectEntity1, subjectEntity2));

        // then
        List<SubjectDto> subject = service.getAll();

        assertEquals(subject.size(), 2);
        assertNotNull(subject.get(0));
        assertNotNull(subject.get(1));
        assertThat(subjectEntity1.getId(), is(subject.get(0).getId()));
        assertThat(subjectEntity1.getPositionX(), is(subject.get(0).getPositionX()));
        assertThat(subjectEntity1.getPositionY(), is(subject.get(0).getPositionY()));
        assertThat(subjectEntity1.getScale(), is(subject.get(0).getScale()));
        assertThat(subjectEntity1.getRotation(), is(subject.get(0).getRotation()));
        assertThat(subjectEntity1.getEditable(), is(subject.get(0).getEditable()));
        assertThat(subjectEntity1.getFloorId(), is(subject.get(0).getFloorId()));
        assertThat(subjectEntity1.getSubjectTemplateEntity().getImage(), is(subject.get(0).getImage()));
    }

    @Test
    public void test_findAllByFloorId_valid() {
        // given
        SubjectEntity subjectEntity1 = SubjectUtilData.subjectEntity();
        SubjectEntity subjectEntity2 = SubjectUtilData.subjectEntity();

        // when
        Mockito.when(repository.findAllByFloorId(FLOOR_ID))
                .thenReturn(List.of(subjectEntity1, subjectEntity2));

        // then
        List<SubjectDto> subject = service.getAllByFloorId(FLOOR_ID);

        assertEquals(subject.size(), 2);
        assertNotNull(subject.get(0));
        assertNotNull(subject.get(1));
        assertThat(subjectEntity1.getId(), is(subject.get(0).getId()));
        assertThat(subjectEntity1.getPositionX(), is(subject.get(0).getPositionX()));
        assertThat(subjectEntity1.getPositionY(), is(subject.get(0).getPositionY()));
        assertThat(subjectEntity1.getScale(), is(subject.get(0).getScale()));
        assertThat(subjectEntity1.getRotation(), is(subject.get(0).getRotation()));
        assertThat(subjectEntity1.getEditable(), is(subject.get(0).getEditable()));
        assertThat(subjectEntity1.getFloorId(), is(subject.get(0).getFloorId()));
        assertThat(subjectEntity1.getSubjectTemplateEntity().getImage(), is(subject.get(0).getImage()));
    }

    @Test
    public void deleteById_Succes() {
        // given
        SubjectEntity entity = SubjectUtilData.subjectEntity();
        // when
        service.delete(entity.getId());
        // then
        verify(repository).deleteById(entity.getId());
    }
}
