package com.vesta.mock.subject;

import com.vesta.common.SubjectUtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.service.SubjectService;
import com.vesta.service.converter.SubjectConverter;
import com.vesta.service.impl.SubjectServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class SubjectTest {

    private SubjectService service;

    @Mock
    private SubjectRepository repository;

    private SubjectConverter converter = new SubjectConverter();

    @Before
    public void setUp() {
        service = new SubjectServiceImpl(repository, converter);
    }

    @Test(expected = VestaException.class)
    public void getById_Invalid() {
        service.getById(null);
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
