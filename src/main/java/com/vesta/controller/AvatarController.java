package com.vesta.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/avatar")
@Api(value = "User Avatar Controller REST Endpoint")
public interface AvatarController {

    @ApiOperation(value = "Save a user-avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User avatar was saved"),
    })
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    void uploadImage(@PathVariable("userId") Long userId, @RequestParam("image") MultipartFile file);

    @ApiOperation(value = "Delete avatar by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Avatar was deleted successfully"),
            @ApiResponse(code = 404, message = "Avatar not found")
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
