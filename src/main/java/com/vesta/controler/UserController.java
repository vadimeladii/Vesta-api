package com.vesta.controler;

import com.vesta.controler.convertor.UserViewConvertor;
import com.vesta.controler.view.UserView;
import com.vesta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserViewConvertor userViewConvertor;

    @GetMapping("/{id}")
    public UserView getById(@PathVariable Long id) {
        return userViewConvertor.convert(userService.getById(id));
    }

    @GetMapping
    public List<UserView> findAll() {
        return userService.findAll()
                .stream()
                .map(userViewConvertor::convert)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserView userView) {
        userService.create(userViewConvertor.deconvert(userView));
    }

    @PutMapping("/{id}")
    public UserView update(@PathVariable("id") Long id, @RequestBody UserView userView) {
        return userViewConvertor.convert(userService.update(id, userViewConvertor.deconvert(userView)));
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        userService.delete(id);
    }

}
