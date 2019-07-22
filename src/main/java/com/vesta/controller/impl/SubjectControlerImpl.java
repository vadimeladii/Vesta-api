package com.vesta.controller.impl;

import com.vesta.controller.SubjectControler;
import com.vesta.controller.convertor.SubjectImageViewConverter;
import com.vesta.controller.view.SubjectImageView;
import com.vesta.service.SubjectImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SubjectControlerImpl implements SubjectControler {

    private final SubjectImageService subjectImageService;
    private final SubjectImageViewConverter converter;

    @Override
    public void deleteImage(Long id){
        subjectImageService.deleteImage(id);
    }

    @Override
    public void createImage(SubjectImageView subjectImageView){
        subjectImageService.createImage(converter.deconvert(subjectImageView));
    }

    @Override
    public SubjectImageView getById (Long id){
        return converter.convert(subjectImageService.getById(id));
    }
}
