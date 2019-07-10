package com.vesta.integration.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.controller.view.CompanyView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.CompanyRepository;
import com.vesta.repository.entity.CompanyEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompanyIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CompanyRepository companyRepository;

    @WithMockUser
    @Test
    public void getListOfCompanies() throws Exception {
        // given
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(1L);
        companyEntity.setName("test");
        companyEntity.setFloor(1);

        companyRepository.save(companyEntity);

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/company")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        List<CompanyView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<CompanyView>>() {});

        // then
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertThat(response.get(0).getId(), is(companyEntity.getId()));
        assertThat(response.get(0).getName(), is(companyEntity.getName()));
        assertThat(response.get(0).getFloor(), is(companyEntity.getFloor()));
    }
}
