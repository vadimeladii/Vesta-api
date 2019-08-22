package com.vesta.controller;

import com.vesta.controller.view.UserCreateView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/admin")
@Api(value = "Admin Controller REST Endpoint")
public interface AdminController {

    @ApiOperation(value = "Modify user data by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PutMapping("/{id}")
    UserCreateView update(@PathVariable("id") Long id, @RequestBody UserCreateView userCreateView);

    @ApiOperation(value = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
