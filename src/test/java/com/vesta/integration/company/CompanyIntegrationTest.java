package com.vesta.integration.company;

import com.vesta.repository.CompanyRepository;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.entity.CompanyEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CompanyIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void whenFindByName_thenReturnCompany() {
        // given
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("test");
        companyEntity.setFloor(1);
        entityManager.persist(companyEntity);
        entityManager.flush();

        // when
        Optional<CompanyEntity> returnEntity = this.companyRepository.findByName("test");

        // then
        assertTrue(returnEntity.isPresent());
        assertThat(companyEntity.getName(), is(returnEntity.get().getName()));
        assertThat(companyEntity.getFloor(), is(returnEntity.get().getFloor()));
    }
}
