package com.vesta.integration.subject;

import com.google.gson.Gson;
import com.vesta.common.SubjectUtilData;
import com.vesta.controller.view.SubjectView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.repository.entity.SubjectTemplateEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static com.vesta.common.SubjectTemplateUtilData.subjectTemplateEntity;
import static com.vesta.common.SubjectUtilData.subjectEntityWithSubjectTemplate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectTest extends IntegrationConfigTest {

    @Autowired
    private SubjectRepository repository;

    @Autowired
    private SubjectTemplateRepository subjectTemplateRepository;

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
}
