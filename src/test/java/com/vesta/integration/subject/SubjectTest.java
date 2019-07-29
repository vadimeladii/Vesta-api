package com.vesta.integration.subject;

import com.vesta.common.SubjectUtilData;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.entity.SubjectEntity;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static com.vesta.common.SubjectUtilData.SUBJECT_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectTest extends IntegrationConfigTest {

    @MockBean
    private SubjectRepository repository;

    @WithMockUser
    @Test
    public void deleteByIdSuccess() throws Exception {

        SubjectEntity entity = SubjectUtilData.subjectEntity();
        repository.save(entity);

        this.mvc.perform(delete("/subject/subjects/{id}", SUBJECT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
