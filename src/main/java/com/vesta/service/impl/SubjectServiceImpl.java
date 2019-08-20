package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.service.SubjectService;
import com.vesta.service.converter.SubjectConverter;
import com.vesta.service.dto.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;
    private final SubjectConverter converter;

    @Override
    public SubjectDto getById(Long id) {
        log.info("method --- getByID");

        SubjectEntity entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("The subject doesn't exist"));
        return converter.convert(entity);
    }

    @Override
    public List<SubjectDto> getAll() {
        log.info("method ---getAll");

        return repository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("method --- delete");

        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(List<Long> ids) {
        log.info("method --- delete");

        repository.deleteAll(ids);
    }

    @Override
    public List<SubjectDto> getByFloorId(Long floorId) {
        log.info("method --- getAllByFloorId");

        return repository.findByFloorId(floorId)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void create(SubjectDto dto) {
        log.info("method --- addSubject");

        repository.save(converter.deconvert(dto));
    }

    @Transactional
    @Override
    public void create(List<SubjectDto> dtos) {
        log.info("method --- addSubjects");

        List<SubjectEntity> entities = dtos
                .stream()
                .map(converter::deconvert)
                .collect(Collectors.toList());
        repository.saveAll(entities);
    }

    @Transactional
    @Override
    public SubjectDto update(Long id, SubjectDto dto) {
        log.info("method --- update");

        SubjectEntity entity = repository.findById(id).orElseThrow(()
                -> new NotFoundException("Subject not found"));

        entity.setPositionX(dto.getPositionX());
        entity.setPositionY(dto.getPositionY());
        entity.setScale(dto.getScale());
        entity.setRotation(dto.getRotation());
        entity.setAdditional(dto.getAdditional());

        return converter.convert(repository.save(entity));
    }
}
