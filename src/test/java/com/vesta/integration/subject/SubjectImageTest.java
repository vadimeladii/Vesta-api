package com.vesta.integration.subject;

import com.google.gson.Gson;
import com.vesta.common.UtilData;
import com.vesta.controller.view.SubjectImageView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.SubjectImageRepository;
import com.vesta.repository.entity.SubjectImageEntity;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static com.vesta.common.UtilData.SUBJECT_IMAGE;
import static com.vesta.common.UtilData.SUBJECT_IMAGE_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectImageTest extends IntegrationConfigTest {

    @MockBean
    private SubjectImageRepository repository;

    @WithMockUser
    @Test
    public void deleteByIdSucces() throws Exception {

        SubjectImageEntity entity = UtilData.subjectImageEntity();
        repository.save(entity);

        this.mvc.perform(delete("/subject/image/{id}", SUBJECT_IMAGE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addImageWithSuccess() throws Exception {

        SubjectImageView subjectImageView = new SubjectImageView();
        subjectImageView.setId(SUBJECT_IMAGE_ID);
        subjectImageView.setImage(SUBJECT_IMAGE);

        Gson gson = new Gson();
        String json = gson.toJson(subjectImageView);

        this.mvc.perform(post("/subject/image/add")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isAccepted());
    }
}
