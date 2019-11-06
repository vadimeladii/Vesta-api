package com.vesta.mock.team;

import com.vesta.common.TeamUtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.TeamRepository;
import com.vesta.repository.entity.TeamEntity;
import com.vesta.service.TeamService;
import com.vesta.service.converter.TeamConverter;
import com.vesta.service.dto.TeamDto;
import com.vesta.service.impl.TeamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)

public class TeamServiceTest {

    private TeamService service;

    @Mock
    private TeamRepository repository;

    private TeamConverter converter = new TeamConverter();

    @Before
    public void setUp() {
        service = new TeamServiceImpl(repository, converter);
    }

    @Test
    public void findById_validId () {
        //given
        TeamEntity teamEntity = TeamUtilData.teamEntity();

        //when
        when(repository.findById(teamEntity.getId())) //1
                .thenReturn(Optional.of(teamEntity));

        // then
        TeamDto dto = service.getById(teamEntity.getId());  //1

        verify(repository).findById(teamEntity.getId());
        assertEquals(dto.getFirstName(), teamEntity.getFirstName());
        assertEquals(dto.getLastName(), teamEntity.getLastName());
        assertEquals(dto.getProjectName(), teamEntity.getProjectName());
        assertEquals(dto.getOffice(), teamEntity.getOffice());
        assertEquals(dto.getPhone(), teamEntity.getPhone());
        assertEquals(dto.getMobilePhone(), teamEntity.getMobilePhone());
        assertEquals(dto.getHasOfficeKeys(), teamEntity.getHasOfficeKeys());
        assertEquals(dto.getWorkAtPentalogSince(), teamEntity.getWorkAtPentalogSince());
        assertEquals(dto.getDateOfBirth(), teamEntity.getDateOfBirth());

    }

    @Test(expected = VestaException.class)
    public void findById_invalidId() {
        service.getById(null);
    }

    @Test
    public void findAllId_valid() {
        //given
        TeamEntity teamEntity1 = TeamUtilData.teamEntity();
        TeamEntity teamEntity2 = TeamUtilData.teamEntity();

        //when
        when(repository.findAll())
                .thenReturn(List.of(teamEntity1, teamEntity2));

        //then
        List<TeamDto> teams = service.findAll();

        assertThat(teams.size(), is(2));
        assertNotNull(teams.get(0));
        assertNotNull(teams.get(1));
        assertThat(teamEntity1.getId(), is(teams.get(0).getId()));
        assertThat(teamEntity1.getFirstName(), is(teams.get(0).getFirstName()));
        assertThat(teamEntity1.getLastName(), is(teams.get(0).getLastName()));
        assertThat(teamEntity1.getProjectName(), is(teams.get(0).getProjectName()));
        assertThat(teamEntity1.getOffice(), is(teams.get(0).getOffice()));
        assertThat(teamEntity1.getPhone(), is(teams.get(0).getPhone()));
        assertThat(teamEntity1.getMobilePhone(), is(teams.get(0).getMobilePhone()));
        assertThat(teamEntity1.getHasOfficeKeys(), is(teams.get(0).getHasOfficeKeys()));
        assertThat(teamEntity1.getWorkAtPentalogSince(), is(teams.get(0).getWorkAtPentalogSince()));
        assertThat(teamEntity1.getDateOfBirth(), is(teams.get(0).getDateOfBirth()));

        assertThat(teamEntity2.getId(), is(teams.get(1).getId()));
        assertThat(teamEntity2.getFirstName(), is(teams.get(1).getFirstName()));
        assertThat(teamEntity2.getLastName(), is(teams.get(1).getLastName()));
        assertThat(teamEntity2.getProjectName(), is(teams.get(1).getProjectName()));
        assertThat(teamEntity2.getOffice(), is(teams.get(1).getOffice()));
        assertThat(teamEntity2.getPhone(), is(teams.get(1).getPhone()));
        assertThat(teamEntity2.getMobilePhone(), is(teams.get(1).getMobilePhone()));
        assertThat(teamEntity2.getHasOfficeKeys(), is(teams.get(1).getHasOfficeKeys()));
        assertThat(teamEntity2.getWorkAtPentalogSince(), is(teams.get(1).getWorkAtPentalogSince()));
        assertThat(teamEntity2.getDateOfBirth(), is(teams.get(1).getDateOfBirth()));
    }
}
