package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.SubjectRepository;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.repository.entity.SubjectTemplateEntity;
import com.vesta.service.SubjectService;
import com.vesta.service.converter.SubjectConverter;
import com.vesta.service.dto.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;
    private final SubjectConverter converter;
    private final SubjectTemplateRepository templateRepository;

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

    @Override
    public List<SubjectDto> getAllByFloorId(Long floorId) {
        log.info("method --- getAllByFloorId");

        return repository.findAllByFloorId(floorId)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void addSubject(SubjectDto dto) {
        log.info("method --- addSubject");

        SubjectEntity entity = converter.deconvert(dto);
        entity.setSubjectTemplateEntity(getTemplateEntity(dto));
        repository.save(entity);
    }

    private SubjectTemplateEntity getTemplateEntity(SubjectDto dto) {

        SubjectTemplateEntity subjectTemplateEntity;
        Optional<SubjectTemplateEntity> templateEntity = templateRepository.getByImage(dto.getImage());

        if (templateEntity.isEmpty()) {
            subjectTemplateEntity = new SubjectTemplateEntity();
            subjectTemplateEntity.setImage(dto.getImage());
            subjectTemplateEntity = templateRepository.save(subjectTemplateEntity);
        } else {
            subjectTemplateEntity = templateEntity.get();
        }
        return subjectTemplateEntity;
    }
}
