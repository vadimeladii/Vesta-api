package com.vesta.mock.company;

import com.vesta.common.CompanyUtilData;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.vesta.common.CompanyUtilData.COMPANY_NAME;
import static com.vesta.common.CompanyUtilData.companyEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    private CompanyConverter companyConverter = new CompanyConverter(new FloorConverter());

    @Mock
    private FloorConverter floorConverter;

    @Mock
    private FloorRepository floorRepository;

    @Before
    public void setUp() {
        companyService = new CompanyServiceImpl(companyRepository, companyConverter, floorConverter, floorRepository);
    }

    @Test
    public void test_findByName_validName() {
        // given
        CompanyEntity companyEntity = companyEntity(COMPANY_NAME);

        // when
        when(companyRepository.findByName(companyEntity.getName()))
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

    @Test
    public void test_findListCompaniesByName_validName() {
        //given
        CompanyEntity companyDB1 = CompanyUtilData.companyEntity();

        CompanyEntity companyDB2 = CompanyUtilData.companyEntity();

        //when
        when(companyRepository.findAll())
                .thenReturn(List.of(companyDB1, companyDB2));

        //then
        List<CompanyDto> all = companyService.findAll();

        verify(companyRepository).findAll();

        assertThat(all.size(), is(2));
        assertNotNull(all.get(0));
        assertNotNull(all.get(1));
        assertThat(companyDB1.getId(), is(all.get(0).getId()));
        assertThat(companyDB1.getName(), is(all.get(0).getName()));
        assertThat(companyDB2.getId(), is(all.get(1).getId()));
        assertThat(companyDB2.getName(), is(all.get(1).getName()));
        assertThat(companyDB1.getFloors().size(), is(1));
        assertThat(companyDB2.getFloors().size(), is(1));
    }

    @Test
    public void test_update_valid(){
        //given
        CompanyDto companyDto = Optional.of(companyConverter.convert(CompanyUtilData.companyEntity()))
                .orElseThrow(() -> new NullPointerException("There is no such company"));

        //when
        when(companyRepository.findById(companyDto.getId())).thenReturn(Optional.of(CompanyUtilData.companyEntity()));

        //then
        companyService.update(companyDto.getId(), companyDto);
    }
}