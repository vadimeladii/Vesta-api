package com.vesta.controller.impl;

import com.vesta.controller.SubjectControler;
import com.vesta.controller.convertor.SubjectTemplateViewConverter;
import com.vesta.controller.view.SubjectTemplateView;
import com.vesta.service.SubjectTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class SubjectControlerImpl implements SubjectControler {

    private final SubjectTemplateService subjectTemplateService;
    private final SubjectTemplateViewConverter converter;

    @Override
    public void deleteImage(Long id){
        subjectTemplateService.deleteImage(id);
    }

    @Override
    public void createImage(@RequestBody SubjectTemplateView subjectTemplateView){
        subjectTemplateService.createImage(converter.deconvert(subjectTemplateView));
    }

    @Override
    public SubjectTemplateView getById (Long id){
        return converter.convert(subjectTemplateService.getById(id));
    }

    @Override
    public List<SubjectTemplateView> getAll(){
        return subjectTemplateService.getAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
