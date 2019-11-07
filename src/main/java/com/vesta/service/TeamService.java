package com.vesta.service;

import com.vesta.service.dto.TeamDto;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;

@Validated
public interface TeamService {

    TeamDto getById (Long id);

    List<TeamDto> findAll();

    void create (@Valid TeamDto teamDto);

    TeamDto update (Long id, @Valid TeamDto teamDto);

    void delete (Long id);
}