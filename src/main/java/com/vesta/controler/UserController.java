package com.vesta.controler;

import com.vesta.controler.convertor.UserViewConvertor;
import com.vesta.service.UserService;
import com.vesta.controler.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserViewConvertor userViewConvertor;

    @GetMapping("/{id}")
    public UserView getById(@PathVariable Long id){
        return userViewConvertor.convertor(userService.getById(id));
    }
}