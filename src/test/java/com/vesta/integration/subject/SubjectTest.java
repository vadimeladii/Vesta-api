package com.vesta.integration.subject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vesta.common.SubjectUtilData;
import com.vesta.controller.view.SubjectUpdateView;
import com.vesta.controller.view.SubjectView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.repository.entity.SubjectTemplateEntity;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.vesta.common.SubjectTemplateUtilData.subjectTemplateEntity;
import static com.vesta.common.SubjectUtilData.subjectEntityWithSubjectTemplate;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectTest extends IntegrationConfigTest {

    @Autowired
    private SubjectRepository repository;

    @Autowired
    private SubjectTemplateRepository subjectTemplateRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void deleteByIdSuccess() throws Exception {

        SubjectTemplateEntity subjectTemplateEntity = subjectTemplateRepository.save(subjectTemplateEntity());
        SubjectEntity entity = repository.save(subjectEntityWithSubjectTemplate(subjectTemplateEntity));

        this.mvc.perform(delete("/subject/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void deleteAllSuccess() throws Exception {

        SubjectTemplateEntity subjectTemplateEntity = subjectTemplateRepository.save(subjectTemplateEntity());
        SubjectEntity entity = repository.save(subjectEntityWithSubjectTemplate(subjectTemplateEntity));

        Gson gson = new Gson();
        String json = gson.toJson(List.of(entity.getId()));

        this.mvc.perform(delete("/subject/ids")
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addSubjectSuccess() throws Exception {

        SubjectView subjectView = SubjectUtilData.subjectView();

        Gson gson = new Gson();
        String json = gson.toJson(subjectView);

        this.mvc.perform(post("/subject")
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @WithMockUser
    @Test
    public void addSubjectsSuccess() throws Exception {

        List<SubjectView> subjectsViews = List.of(SubjectUtilData.subjectView());

        Gson gson = new Gson();
        String json = gson.toJson(subjectsViews);

        this.mvc.perform(post("/subject/list")
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @WithMockUser
    @Test
    public void test_getById_Valid() throws Exception {

        SubjectTemplateEntity subjectTemplateEntity = subjectTemplateRepository.save(subjectTemplateEntity());
        SubjectEntity entity = repository.save(SubjectUtilData.subjectEntityWithSubjectTemplate(subjectTemplateEntity));

        MvcResult mvcResult = mvc.perform(get("/subject/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        SubjectView response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), SubjectView.class);

        assertNotNull(response);
        Assert.assertThat(response.getId(), CoreMatchers.is(entity.getId()));
        Assert.assertThat(response.getPosition(), CoreMatchers.is(List.of(entity.getPositionX(), entity.getPositionY())));
        Assert.assertThat(response.getEditable(), CoreMatchers.is(entity.getEditable()));
        Assert.assertThat(response.getFloorId(), CoreMatchers.is(entity.getFloorId()));
        Assert.assertThat(response.getRotation(), CoreMatchers.is(entity.getRotation()));
        Assert.assertThat(response.getScale(), CoreMatchers.is(entity.getScale()));
        Assert.assertThat(response.getImage(), CoreMatchers.is(entity.getSubjectTemplateEntity().getImage()));
        Assert.assertThat(convertToJson(response.getAdditional()), CoreMatchers.is(entity.getAdditional()));
    }


    @WithMockUser
    @Test
    public void test_getAll() throws Exception {

        SubjectTemplateEntity subjectTemplateEntity = subjectTemplateRepository.save(subjectTemplateEntity());
        SubjectEntity entity = repository.save(SubjectUtilData.subjectEntityWithSubjectTemplate(subjectTemplateEntity));

        MvcResult mvcResult = mvc.perform(get("/subject")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        List<SubjectView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<SubjectView>>() {
                });

        assertNotNull(response);
        assertNotNull(response.get(0));
        assertNotNull(response);
        Assert.assertThat(response.get(0).getId(), CoreMatchers.is(entity.getId()));
        Assert.assertThat(response.get(0).getPosition(), CoreMatchers.is(List.of(entity.getPositionX(), entity.getPositionY())));
        Assert.assertThat(response.get(0).getEditable(), CoreMatchers.is(entity.getEditable()));
        Assert.assertThat(response.get(0).getFloorId(), CoreMatchers.is(entity.getFloorId()));
        Assert.assertThat(response.get(0).getRotation(), CoreMatchers.is(entity.getRotation()));
        Assert.assertThat(response.get(0).getScale(), CoreMatchers.is(entity.getScale()));
        Assert.assertThat(response.get(0).getImage(), CoreMatchers.is(entity.getSubjectTemplateEntity().getImage()));
        Assert.assertThat(convertToJson(response.get(0).getAdditional()), CoreMatchers.is(entity.getAdditional()));
    }

    @WithMockUser
    @Test
    public void test_getAll_ByFloorId() throws Exception {

        SubjectTemplateEntity subjectTemplateEntity = subjectTemplateRepository.save(subjectTemplateEntity());
        SubjectEntity entity = repository.save(SubjectUtilData.subjectEntityWithSubjectTemplate(subjectTemplateEntity));

        MvcResult mvcResult = mvc.perform(get("/subject/floor/{floorId}", entity.getFloorId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // then
        List<SubjectView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<SubjectView>>() {
                });

        assertNotNull(response);
        assertNotNull(response.get(0));
        assertNotNull(response);
        Assert.assertThat(response.get(0).getId(), CoreMatchers.is(entity.getId()));
        Assert.assertThat(response.get(0).getPosition(), CoreMatchers.is(List.of(entity.getPositionX(), entity.getPositionY())));
        Assert.assertThat(response.get(0).getEditable(), CoreMatchers.is(entity.getEditable()));
        Assert.assertThat(response.get(0).getFloorId(), CoreMatchers.is(entity.getFloorId()));
        Assert.assertThat(response.get(0).getRotation(), CoreMatchers.is(entity.getRotation()));
        Assert.assertThat(response.get(0).getScale(), CoreMatchers.is(entity.getScale()));
        Assert.assertThat(response.get(0).getImage(), CoreMatchers.is(entity.getSubjectTemplateEntity().getImage()));
        Assert.assertThat(convertToJson(response.get(0).getAdditional()), CoreMatchers.is(entity.getAdditional()));
    }

    @WithMockUser
    @Test
    public void test_update_ById() throws Exception {

        SubjectTemplateEntity subjectTemplateEntity = subjectTemplateRepository.save(subjectTemplateEntity());
        SubjectEntity entity = repository.save(SubjectUtilData.subjectEntityWithSubjectTemplate(subjectTemplateEntity));

        SubjectUpdateView subjectUpdateView = SubjectUtilData.subjectUpdateView();

        Gson gson = new Gson();
        String json = gson.toJson(subjectUpdateView);

        this.mvc.perform(put("/subject/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
