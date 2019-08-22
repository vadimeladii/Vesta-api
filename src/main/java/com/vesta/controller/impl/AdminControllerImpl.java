package com.vesta.controller.impl;

import com.vesta.controller.AdminController;
import com.vesta.controller.convertor.UserCreateViewConverter;
import com.vesta.controller.view.UserCreateView;
import com.vesta.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;
    private final UserCreateViewConverter userCreateViewConverter;

    @Override
    public UserCreateView update(Long id, UserCreateView userCreateView) {
        return userCreateViewConverter.convert(adminService.update(id, userCreateViewConverter.deconvert(userCreateView)));
    }

    @Override
    public void delete(Long id) {
        adminService.delete(id);
    }
}
