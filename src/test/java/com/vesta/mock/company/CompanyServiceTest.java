package com.vesta.mock.company;

import com.vesta.exception.VestaException;
import com.vesta.repository.CompanyRepository;
import com.vesta.repository.entity.CompanyEntity;
import com.vesta.service.CompanyService;
import com.vesta.service.converter.CompanyConverter;
import com.vesta.service.dto.CompanyDto;
import com.vesta.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static com.vesta.integration.common.UtilIntegration.createCompayEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    private CompanyConverter companyConverter = new CompanyConverter();

    @BeforeEach
    public void setUp() {
        companyService = new CompanyServiceImpl(companyRepository, companyConverter);
    }

    @Test
    public void test_findByName_validName() {
        // given
        CompanyEntity companyEntity = createCompayEntity();

        Mockito.when(companyRepository.findByName(companyEntity.getName()))
                .thenReturn(Optional.of(companyEntity));

        // when
        CompanyDto returnDto = companyService.getByName("test");

        // then
        assertNotNull(returnDto);
        assertThat(companyEntity.getName(), is(returnDto.getName()));
        assertThat(companyEntity.getFloor(), is(returnDto.getFloor()));
        verify(companyRepository).findByName(companyEntity.getName());
    }

    @Test
    public void test_findByName_invalidName() {
        // given
        CompanyEntity companyEntity = createCompayEntity();

        Mockito.when(companyRepository.findByName(companyEntity.getName()))
                .thenReturn(Optional.of(companyEntity));

        // when
        Assertions.assertThrows(VestaException.class, () -> companyService.getByName("invalidName"));
    }
}
