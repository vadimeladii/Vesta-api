package com.vesta.service.converter;


import com.vesta.repository.entity.TeamEntity;
import com.vesta.service.dto.TeamDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeamConverter implements Converter<TeamEntity, TeamDto>{

    @Override
    public TeamDto convert(TeamEntity entity) {
        if (entity == null) return null;
        TeamDto dto = new TeamDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setProjectName(entity.getProjectName());
        dto.setOffice(entity.getOffice());
        dto.setPhone(entity.getPhone());
        dto.setMobilePhone(entity.getMobilePhone());
        dto.setHasOfficeKeys(entity.getHasOfficeKeys());
        dto.setWorkAtPentalogSince(entity.getWorkAtPentalogSince());
        dto.setDateOfBirth(entity.getDateOfBirth());


        return dto;
    }

    public TeamEntity deconvert(TeamDto teamDto) {
        if (teamDto == null) return null;
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setFirstName(teamDto.getFirstName());
        teamEntity.setLastName(teamDto.getLastName());
        teamEntity.setProjectName(teamDto.getProjectName());
        teamEntity.setOffice(teamDto.getOffice());
        teamEntity.setPhone(teamDto.getPhone());
        teamEntity.setMobilePhone(teamDto.getMobilePhone());
        teamEntity.setHasOfficeKeys(teamDto.getHasOfficeKeys());
        teamEntity.setWorkAtPentalogSince(teamDto.getWorkAtPentalogSince());
        teamEntity.setDateOfBirth(teamDto.getDateOfBirth());

        return teamEntity;
    }
}
