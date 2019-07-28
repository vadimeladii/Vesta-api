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

import static com.vesta.common.CompanyUtilData.companyEntity;
import static com.vesta.common.CompanyUtilData.companyEntityWithoutFloors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class  CompanyIntegrationTest extends IntegrationConfigTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void getListOfCompanies() throws Exception {
        // given
        CompanyEntity companyEntity = companyEntityWithoutFloors();
        companyRepository.save(companyEntity);

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/company")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        List<CompanyView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<CompanyView>>() {
                });

        assertNotNull(response);
        assertNotNull(response.get(0));
        assertThat(response.get(0).getId(), is(1L));
        assertThat(response.get(0).getName(), is(companyEntity.getName()));
    }
}
