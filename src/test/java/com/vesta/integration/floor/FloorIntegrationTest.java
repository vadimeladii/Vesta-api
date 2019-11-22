package com.vesta.integration.floor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vesta.common.CompanyUtilData;
import com.vesta.common.FloorUtilData;
import com.vesta.controller.view.FloorView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.CompanyRepository;
import com.vesta.repository.FloorRepository;
import com.vesta.repository.entity.CompanyEntity;
import com.vesta.repository.entity.FloorEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.vesta.common.CompanyUtilData.companyEntityWithoutFloors;
import static com.vesta.common.FloorUtilData.floorView;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class  FloorIntegrationTest extends IntegrationConfigTest {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void getListOfFloors() throws Exception {
        // given
        CompanyEntity companyEntity = companyEntityWithoutFloors();
        CompanyEntity companyDB = companyRepository.save(companyEntity);

        FloorEntity floorEntity = floorRepository.save(FloorUtilData.floorEntity(companyDB.getId()));

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/floor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        List<FloorView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<FloorView>>() {
                });

        // then
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertThat(response.get(0).getId(), is(floorEntity.getId()));
        assertThat(response.get(0).getName(), is(floorEntity.getName()));
        assertThat(response.get(0).getCompanyId(), is(floorEntity.getCompanyId()));
    }

    @WithMockUser
    @Test
    public void updateFloorsById_Success() throws Exception {
        // given
        CompanyEntity companyEntity = companyRepository.save(CompanyUtilData.companyEntityWithoutFloors());
        FloorEntity floorEntity = floorRepository.save(FloorUtilData.floorEntity(companyEntity.getId()));

        String updateFloorName = "Floor Test";
        FloorView view = floorView();
        view.setName(updateFloorName);

        Gson gson = new Gson();
        String json = gson.toJson(view);

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/floor/" + floorEntity.getId())
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        FloorView floorView = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), FloorView.class);

        assertNotNull(floorView);
        assertThat(floorView.getName(), is(updateFloorName));
    }

    @WithMockUser
    @Test
    public void updateFloorById_NotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/floor/" + RandomStringUtils.randomNumeric(10))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}