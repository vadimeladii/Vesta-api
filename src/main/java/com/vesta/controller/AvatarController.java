package com.vesta.controller;

import com.vesta.controller.view.AvatarView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ApiOperation(value = "Returns the image by userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user avatar by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @ResponseBody
    @GetMapping("/user/{userId}")
    AvatarView getByUserId(@PathVariable Long userId);

    @ResponseBody
    @GetMapping("/user/{userId}/avatar")
    ResponseEntity getAvatarByUserId(@PathVariable Long userId);
}
