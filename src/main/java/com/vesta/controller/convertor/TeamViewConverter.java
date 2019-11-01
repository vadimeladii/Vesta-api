package com.vesta.controller.convertor;

import com.vesta.controller.view.TeamView;
import com.vesta.service.dto.TeamDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeamViewConverter implements Converter<TeamDto, TeamView> {

    @Override
    public TeamView convert(TeamDto dto) {
        if (dto == null)
        return null;

        TeamView view = new TeamView();
        view.setId(dto.getId());
        view.setFirstName(dto.getFirstName());
        view.setLastName(dto.getLastName());
        view.setProjectName(dto.getProjectName());
        view.setOffice(dto.getOffice());
        view.setPhone(dto.getPhone());
        view.setMobilePhone(dto.getMobilePhone());
        view.setHasOfficeKeys(dto.getHasOfficeKeys());
        view.setWorkAtPentalogSince(dto.getWorkAtPentalogSince());
        view.setDateOfBirth(dto.getDateOfBirth());
        return view;
    }
}
