package com.vesta.controller;

import com.vesta.controller.view.SubjectImageView;
import com.vesta.service.dto.SubjectImageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@Api(value = "Subject Controller REST Endpoint")
public interface SubjectControler {

    @ApiOperation(value = "Delete the image by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image deleted with success"),
    })
    @DeleteMapping("/image/{id}")
    void deleteImage(@PathVariable("id") Long id);

    @ApiOperation(value = "Add the image")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Image added with success"),
    })
    @PostMapping("/image/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void createImage(SubjectImageView subjectImageView);

    @ApiOperation(value = "Returns the image by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get image by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Image not found")
    })
    @ResponseBody
    @GetMapping("/image/{id}")
    SubjectImageView getById(@PathVariable("id") Long id);

    @ApiOperation(value = "Returns the all images")
    @GetMapping("/image/all")
    List<SubjectImageView> getAll();
}