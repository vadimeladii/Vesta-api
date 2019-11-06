package com.vesta.integration.team;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vesta.common.TeamUtilData;
import com.vesta.controller.view.TeamView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.TeamRepository;
import com.vesta.repository.entity.TeamEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.vesta.common.TeamUtilData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TeamIntegrationTest  extends IntegrationConfigTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void getListOfTeams() throws Exception {

        // given
        TeamEntity teamEntity = teamRepository.save(teamEntity());


        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/team")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        List<TeamView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<TeamView>>() {
                });

        assertNotNull(response);
        assertNotNull(response.get(0));
        assertThat(response.get(0).getId(), is(teamEntity.getId()));
        assertThat(response.get(0).getFirstName(), is(teamEntity.getFirstName()));
        assertThat(response.get(0).getLastName(), is(teamEntity.getLastName()));
        assertThat(response.get(0).getProjectName(), is(teamEntity.getProjectName()));
        assertThat(response.get(0).getOffice(), is(teamEntity.getOffice()));
        assertThat(response.get(0).getPhone(), is(teamEntity.getPhone()));
        assertThat(response.get(0).getMobilePhone(), is(teamEntity.getMobilePhone()));
        assertThat(response.get(0).getHasOfficeKeys(), is(teamEntity.getHasOfficeKeys()));
        assertThat(response.get(0).getWorkAtPentalogSince(), is(teamEntity.getWorkAtPentalogSince()));
        assertThat(response.get(0).getDateOfBirth(), is(teamEntity.getDateOfBirth()));

    }

    @WithMockUser
    @Test
    public void getTeamById_Success() throws Exception {
        // given
        TeamEntity teamEntity = teamRepository.save(teamEntity());

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/team/" + teamEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        TeamView teamView = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), TeamView.class);

        assertNotNull(teamView);
        assertThat(teamView.getFirstName(), is(teamEntity.getFirstName()));
        assertThat(teamView.getLastName(), is(teamEntity.getLastName()));
        assertThat(teamView.getProjectName(), is(teamEntity.getProjectName()));
        assertThat(teamView.getOffice(), is(teamEntity.getOffice()));
        assertThat(teamView.getPhone(), is(teamEntity.getPhone()));
        assertThat(teamView.getMobilePhone(), is(teamEntity.getMobilePhone()));
        assertThat(teamView.getHasOfficeKeys(), is(teamEntity.getHasOfficeKeys()));
        assertThat(teamView.getWorkAtPentalogSince(), is(teamEntity.getWorkAtPentalogSince()));
        assertThat(teamView.getDateOfBirth(), is(teamEntity.getDateOfBirth()));

    }

    @WithMockUser
    @Test
    public void getTeamByID_NotFound() throws Exception {
        // when
        mvc.perform(MockMvcRequestBuilders.get("/team/" + RandomStringUtils.randomNumeric(10))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser
    @Test
    public void createTeam() throws  Exception {

        TeamView teamView = new TeamView();
        teamView.setId(TEAM_ID);
        teamView.setFirstName(FIRST_NAME);
        teamView.setLastName(LAST_NAME);
        teamView.setProjectName(TEAM_NAME);
        teamView.setOffice(OFFICE_NAME);
        teamView.setPhone(PHONE_NUMBER);
        teamView.setMobilePhone(MOBILE_PHONE);
        teamView.setHasOfficeKeys(OFFICE_KEYS);
        teamView.setWorkAtPentalogSince(WORK_SINCE);
        teamView.setDateOfBirth(BIRTH_DAY);

        Gson gson = new Gson();
        String json = gson.toJson(teamView);

        this.mvc.perform(post("/team/registration")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @WithMockUser
    @Test
    public void deleteById_Success() throws Exception {

        TeamEntity entity = teamRepository.save(TeamUtilData.teamEntity());

        this.mvc.perform(delete("/team/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void deleteById_NotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/team/" + RandomStringUtils.randomNumeric(10))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser
    @Test
    public void updateById_Success() throws Exception {
        String updateFirstName = "Name Test";
        String updateLastName = "lastName";
        String updateProjectName = "Vesta-api";
        String updateOffice = "CHi-313";
        String updatePhone = "022210210";
        String updateMobilePhone = "079799799";
        String updateHasOfficeKeys = "No";
        String updateWorkAtPentalogSince = "2019 Test";
        String updateDateOfBirth = "01.01.1991";

        TeamEntity entity = teamRepository.save(teamEntity());

        TeamView view = teamView();
        view.setFirstName(updateFirstName);
        view.setLastName(updateLastName);
        view.setProjectName(updateProjectName);
        view.setOffice(updateOffice);
        view.setPhone(updatePhone);
        view.setMobilePhone(updateMobilePhone);
        view.setHasOfficeKeys(updateHasOfficeKeys);
        view.setWorkAtPentalogSince(updateWorkAtPentalogSince);
        view.setDateOfBirth(updateDateOfBirth);

        Gson gson = new Gson();
        String json = gson.toJson(view);

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/team/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        TeamView teamView = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), TeamView.class);

        assertNotNull(teamView);
        assertThat(teamView.getFirstName(), is(updateFirstName));
        assertThat(teamView.getLastName(), is(updateLastName));
        assertThat(teamView.getProjectName(), is(updateProjectName));
        assertThat(teamView.getOffice(), is(updateOffice));
        assertThat(teamView.getPhone(), is(updatePhone));
        assertThat(teamView.getMobilePhone(), is(updateMobilePhone));
        assertThat(teamView.getHasOfficeKeys(), is(updateHasOfficeKeys));
        assertThat(teamView.getWorkAtPentalogSince(), is(updateWorkAtPentalogSince));
        assertThat(teamView.getDateOfBirth(), is(updateDateOfBirth));
    }

    @WithMockUser
    @Test
    public void modifyById_NotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/team/" + RandomStringUtils.randomNumeric(10))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
