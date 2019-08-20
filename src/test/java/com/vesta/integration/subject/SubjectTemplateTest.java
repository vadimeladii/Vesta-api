package com.vesta.integration.subject;

import com.google.gson.Gson;
import com.vesta.common.SubjectTemplateUtilData;
import com.vesta.controller.view.SubjectTemplateView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectTemplateEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static com.vesta.common.SubjectTemplateUtilData.SUBJECT_IMAGE;
import static com.vesta.common.SubjectTemplateUtilData.SUBJECT_IMAGE_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectTemplateTest extends IntegrationConfigTest {

    @Autowired
    private SubjectTemplateRepository repository;

    @WithMockUser
    @Test
    public void deleteByIdSuccess() throws Exception {

        SubjectTemplateEntity entity = repository.save(SubjectTemplateUtilData.subjectTemplateEntity());

        this.mvc.perform(delete("/subjectTemplate/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addSubjectTemplateWithSuccess() throws Exception {

        SubjectTemplateView subjectTemplateView = new SubjectTemplateView();
        subjectTemplateView.setId(SUBJECT_IMAGE_ID);
        subjectTemplateView.setImage(SUBJECT_IMAGE);

        Gson gson = new Gson();
        String json = gson.toJson(subjectTemplateView);

        this.mvc.perform(post("/subjectTemplate")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @WithMockUser
    @Test
    public void test_getById_Valid() throws Exception {

        SubjectTemplateEntity entity = repository.save(SubjectTemplateUtilData.subjectTemplateEntity());

        this.mvc.perform(get("/subjectTemplate/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void test_getAll() throws Exception {
        repository.save(SubjectTemplateUtilData.subjectTemplateEntity());
        repository.save(SubjectTemplateUtilData.subjectTemplateEntity());

        this.mvc.perform(get("/subjectTemplate/all")
                .contentType(MediaType.ALL)
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }
}
