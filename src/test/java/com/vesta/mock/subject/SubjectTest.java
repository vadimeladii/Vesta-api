package com.vesta.mock.subject;

import com.vesta.common.SubjectUtilData;
import com.vesta.exception.NotFoundException;
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

    private SubjectConverter converter = new SubjectConverter(templateRepository);

    @Before
    public void setUp() {
        service = new SubjectServiceImpl(repository, converter);
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
        assertThat(entity.getAdditional(), is(returnDto.getAdditional()));
        assertThat(entity.getSubjectTemplateEntity().getImage(), is(returnDto.getImage()));
        assertThat(entity.getAdditional(), is(returnDto.getAdditional()));
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
        assertThat(subjectEntity1.getAdditional(), is(subject.get(0).getAdditional()));
        assertThat(subjectEntity1.getSubjectTemplateEntity().getImage(), is(subject.get(0).getImage()));
        assertThat(subjectEntity1.getAdditional(), is(subject.get(0).getAdditional()));
    }

    @Test
    public void test_findAllByFloorId_valid() {
        // given
        SubjectEntity subjectEntity1 = SubjectUtilData.subjectEntity();
        SubjectEntity subjectEntity2 = SubjectUtilData.subjectEntity();

        // when
        Mockito.when(repository.findByFloorId(FLOOR_ID))
                .thenReturn(List.of(subjectEntity1, subjectEntity2));

        // then
        List<SubjectDto> subject = service.getByFloorId(FLOOR_ID);

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
        assertThat(subjectEntity1.getAdditional(), is(subject.get(0).getAdditional()));
        assertThat(subjectEntity1.getSubjectTemplateEntity().getImage(), is(subject.get(0).getImage()));
        assertThat(subjectEntity1.getAdditional(), is(subject.get(0).getAdditional()));
    }

    @Test
    public void deleteById_Success() {
        // given
        SubjectEntity entity = SubjectUtilData.subjectEntity();
        // when
        service.delete(entity.getId());
        // then
        verify(repository).deleteById(entity.getId());
    }

    @Test(expected = NotFoundException.class)
    public void test_updateSubject_invalidId() {
        // given
        SubjectDto dto = SubjectUtilData.subjectDto();

        // when
        Mockito.when(repository.findById(dto.getId()))
                .thenReturn(Optional.empty());

        // then
        service.update(dto.getId(), dto);
    }

    @Test
    public void test_update_valid() {
        // given
        SubjectDto dto = SubjectUtilData.subjectDto();

        // when
        Mockito.when(repository.findById(dto.getId()))
                .thenReturn(Optional.of(SubjectUtilData.subjectEntity()));

        // then
        service.update(dto.getId(), dto);
    }
}
