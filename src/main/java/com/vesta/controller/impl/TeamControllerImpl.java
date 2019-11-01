package com.vesta.controller.impl;

import com.vesta.controller.TeamController;
import com.vesta.controller.convertor.TeamCreateViewConverter;
import com.vesta.controller.convertor.TeamViewConverter;
import com.vesta.controller.view.TeamCreateView;
import com.vesta.controller.view.TeamView;
import com.vesta.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TeamControllerImpl implements TeamController {

    private final TeamService teamService;
    private final TeamCreateViewConverter teamCreateViewConverter;
    private final TeamViewConverter teamViewConverter;

    @Override
    public TeamView getById(Long id) {
        return teamViewConverter.convert(teamService.getById(id));
    }

    @Override
    public List<TeamView> findAll() {
        return teamService.findAll()
                .stream()
                .map(teamViewConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public TeamCreateView update(Long id, TeamCreateView teamCreateView) {
        return teamCreateViewConverter.convert(teamService.update(id, teamCreateViewConverter.deconvert(teamCreateView)));
    }

    @Override
    public void create(TeamCreateView teamCreateView) {
        teamService.create(teamCreateViewConverter.deconvert(teamCreateView));
    }

    @Override
    public void delete(Long id) {
        teamService.delete(id);
    }

}
