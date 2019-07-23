package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.SubjectImageRepository;
import com.vesta.repository.entity.SubjectImageEntity;
import com.vesta.service.SubjectImageService;
import com.vesta.service.converter.SubjectImageConverter;
import com.vesta.service.dto.SubjectImageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SubjectImageServiceImpl implements SubjectImageService {

    private final SubjectImageRepository subjectImageRepository;
    private final SubjectImageConverter converter;

    @Override
    public SubjectImageDto getById(Long id) {
        log.info("method --- getByID");

        SubjectImageEntity subjectImageEntity = subjectImageRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The Image doesn't exist"));
        return converter.convert(subjectImageEntity);
    }

    @Override
    public void createImage(SubjectImageDto subjectImageDto) {
        log.info("method --- createImage");

        SubjectImageEntity subjectImageEntity = converter.deconvert(subjectImageDto);
        subjectImageRepository.save(subjectImageEntity);
    }

    @Override
    public void deleteImage(Long id) {
        log.info("method --- deleteImage");

        subjectImageRepository.deleteById(id);
    }

    @Override
    public List<SubjectImageDto> getAll() {
        log.info("method --- findAll");

        return subjectImageRepository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
