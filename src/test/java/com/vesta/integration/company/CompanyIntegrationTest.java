package com.vesta.integration.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vesta.controller.view.CompanyView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.CompanyRepository;
import com.vesta.repository.entity.CompanyEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.vesta.common.CompanyUtilData.companyEntityWithoutFloors;
import static com.vesta.common.CompanyUtilData.companyViewWithoutFloors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class  CompanyIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void getListOfCompanies() throws Exception {
        // given
        CompanyEntity companyEntity = companyRepository.save(companyEntityWithoutFloors());

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
        assertThat(response.get(0).getId(), is(companyEntity.getId()));
        assertThat(response.get(0).getName(), is(companyEntity.getName()));
    }

    @WithMockUser
    @Test
    public void getCompanyByID_Success() throws Exception {
        // given
        CompanyEntity companyEntity = companyRepository.save(companyEntityWithoutFloors());

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/company/" + companyEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        CompanyView companyView = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), CompanyView.class);

        assertNotNull(companyView);
        assertThat(companyView.getId(), is(companyEntity.getId()));
        assertThat(companyView.getName(), is(companyEntity.getName()));
    }

    @WithMockUser
    @Test
    public void getCompanyByID_NotFound() throws Exception {
        // when
        mvc.perform(MockMvcRequestBuilders.get("/company/" + RandomStringUtils.randomNumeric(10))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser
    @Test
    public void addCompany() throws Exception {
        // given
        CompanyView companyView = companyViewWithoutFloors();

        Gson gson = new Gson();
        String json = gson.toJson(companyView);

        // when
        mvc.perform(MockMvcRequestBuilders.post("/company")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andReturn();
    }

}
