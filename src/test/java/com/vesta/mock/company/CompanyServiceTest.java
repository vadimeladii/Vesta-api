package com.vesta.mock.company;

import com.vesta.exception.VestaException;
import com.vesta.repository.CompanyRepository;
import com.vesta.repository.FloorRepository;
import com.vesta.repository.entity.CompanyEntity;
import com.vesta.service.CompanyService;
import com.vesta.service.converter.CompanyConverter;
import com.vesta.service.converter.FloorConverter;
import com.vesta.service.dto.CompanyDto;
import com.vesta.service.impl.CompanyServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.vesta.common.CompanyUtilData.COMPANY_NAME;
import static com.vesta.common.CompanyUtilData.companyEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private FloorConverter converter;

    @Mock
    private FloorRepository repository;

    @Mock
    private CompanyRepository companyRepository;

    private CompanyConverter companyConverter = new CompanyConverter(new FloorConverter());

    @Before
    public void setUp() {
        companyService = new CompanyServiceImpl(companyRepository, companyConverter, converter, repository);
    }

    @Test
    public void test_findByName_validName() {
        // given
        CompanyEntity companyEntity = companyEntity(COMPANY_NAME);

        // when
        Mockito.when(companyRepository.findByName(companyEntity.getName()))
                .thenReturn(Optional.of(companyEntity));

        // then
        CompanyDto returnDto = companyService.getByName(COMPANY_NAME);

        assertNotNull(returnDto);
        assertThat(companyEntity.getName(), is(returnDto.getName()));
        assertThat(companyEntity.getFloors().size(), is(1));
        verify(companyRepository).findByName(companyEntity.getName());
    }

    @Test(expected = VestaException.class)
    public void test_findByName_invalidName() {
        companyService.getByName(RandomStringUtils.randomAlphabetic(10));
    }
}
