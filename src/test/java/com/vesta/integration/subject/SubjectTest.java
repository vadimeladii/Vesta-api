package com.vesta.integration.subject;

import com.google.gson.Gson;
import com.vesta.common.SubjectUtilData;
import com.vesta.controller.view.SubjectView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.entity.SubjectEntity;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static com.vesta.common.SubjectUtilData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectTest extends IntegrationConfigTest {

    @MockBean
    private SubjectRepository repository;

    @WithMockUser
    @Test
    public void deleteByIdSuccess() throws Exception {

        SubjectEntity entity = SubjectUtilData.subjectEntity();
        repository.save(entity);

        this.mvc.perform(delete("/subject/{id}", SUBJECT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void deleteAllSuccess() throws Exception {

        SubjectEntity entity = SubjectUtilData.subjectEntity();
        repository.save(entity);

        Gson gson = new Gson();
        String json = gson.toJson(List.of(SUBJECT_ID));

        this.mvc.perform(delete("/subject/ids")
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addSubjectSuccess() throws Exception {

        SubjectView subjectView = new SubjectView();

        subjectView.setPosition(List.of(SUBJECT_POSITION_X, SUBJECT_POSITION_Y));
        subjectView.setScale(SUBJECT_SCALE);
        subjectView.setRotation(SUBJECT_ROTATION);
        subjectView.setEditable(SUBJECT_EDITABLE);
        subjectView.setFloorId(FLOOR_ID);
        subjectView.setUtilities(UTILITIES);
        subjectView.setImage(TEMPLATE);

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
