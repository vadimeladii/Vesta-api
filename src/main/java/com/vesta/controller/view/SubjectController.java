package com.vesta.controller.view;

import java.util.List;

public interface SubjectController {
    void delete(Long id);

    SubjectView getById(Long id);

    List<SubjectView> getAll();
}
