package com.vesta.controller.convertor;

import com.vesta.controller.view.TeamCreateView;
import com.vesta.service.dto.TeamDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeamCreateViewConverter implements Converter<TeamDto, TeamCreateView> {

    @Override
    public TeamCreateView convert(TeamDto dto) {
        if (dto == null) return null;
        TeamCreateView view = new TeamCreateView();
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

    public TeamDto deconvert(TeamCreateView view) {
        if (view == null) return null;
        TeamDto dto = new TeamDto();
        dto.setFirstName(view.getFirstName());
        dto.setLastName(view.getLastName());
        dto.setProjectName(view.getProjectName());
        dto.setOffice(view.getOffice());
        dto.setPhone(view.getPhone());
        dto.setMobilePhone(view.getMobilePhone());
        dto.setHasOfficeKeys(view.getHasOfficeKeys());
        dto.setWorkAtPentalogSince(view.getWorkAtPentalogSince());
        dto.setDateOfBirth(view.getDateOfBirth());
        return dto;
    }
}