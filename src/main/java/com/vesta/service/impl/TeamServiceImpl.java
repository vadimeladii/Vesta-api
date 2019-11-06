package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.TeamRepository;
import com.vesta.repository.entity.TeamEntity;
import com.vesta.service.TeamService;
import com.vesta.service.converter.TeamConverter;
import com.vesta.service.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;
    private final TeamConverter converter;


    @Override
    public TeamDto getById(Long id) {
        TeamEntity teamEntity = repository.findById(id).orElseThrow(() ->
                new NotFoundException("The team doesn't exist"));

        return converter.convert(teamEntity);
    }

    @Override
    public List<TeamDto> findAll() {
        return repository.findAll().stream().map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void create(TeamDto dto) {
        repository.save(converter.deconvert(dto));
    }

    @Transactional
    @Override
    public TeamDto update(Long id, @Valid TeamDto teamDto) {
        TeamEntity teamEntity = repository.findById(id).orElseThrow(() ->
                new NotFoundException("The team doesn't exist"));

        teamEntity.setFirstName(teamDto.getFirstName());
        teamEntity.setLastName(teamDto.getLastName());
        teamEntity.setProjectName(teamDto.getProjectName());
        teamEntity.setOffice(teamDto.getOffice());
        teamEntity.setPhone(teamDto.getPhone());
        teamEntity.setMobilePhone(teamDto.getMobilePhone());
        teamEntity.setHasOfficeKeys(teamDto.getHasOfficeKeys());
        teamEntity.setWorkAtPentalogSince(teamDto.getWorkAtPentalogSince());
        teamEntity.setDateOfBirth(teamDto.getDateOfBirth());

        return converter.convert(repository.save(teamEntity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
