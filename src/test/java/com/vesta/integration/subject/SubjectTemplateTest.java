package com.vesta.integration.subject;

import com.google.gson.Gson;
import com.vesta.common.SubjectTemplateUtilData;
import com.vesta.controller.view.SubjectTemplateView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectTemplateEntity;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static com.vesta.common.SubjectTemplateUtilData.SUBJECT_IMAGE;
import static com.vesta.common.SubjectTemplateUtilData.SUBJECT_IMAGE_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectTemplateTest extends IntegrationConfigTest {

    @MockBean
    private SubjectTemplateRepository repository;

    @WithMockUser
    @Test
    public void deleteByIdSuccess() throws Exception {

        SubjectTemplateEntity entity = SubjectTemplateUtilData.subjectTemplateEntity();
        repository.save(entity);

        this.mvc.perform(delete("/subject/image/{id}", SUBJECT_IMAGE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addImageWithSuccess() throws Exception {

        SubjectTemplateView subjectTemplateView = new SubjectTemplateView();
        subjectTemplateView.setId(SUBJECT_IMAGE_ID);
        subjectTemplateView.setImage(SUBJECT_IMAGE);

        Gson gson = new Gson();
        String json = gson.toJson(subjectTemplateView);

        this.mvc.perform(post("/subject/image/add")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isAccepted());
    }
}
