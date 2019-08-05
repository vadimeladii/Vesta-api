package com.vesta.controller;

import com.vesta.controller.view.SubjectTemplateView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subjectTemplate")
@Api(value = "Subject Controller REST Endpoint")
public interface SubjectTemplateController {

    @ApiOperation(value = "Returns the image by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get image by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Image not found")
    })
    @ResponseBody
    @GetMapping("/{id}")
    SubjectTemplateView getById(@PathVariable("id") Long id);

    @ApiOperation(value = "Returns the all images")
    @GetMapping("/all")
    List<SubjectTemplateView> getAll();

    @ApiOperation(value = "Add the subject template")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Subject Template added with success"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(SubjectTemplateView subjectTemplateView);

    @ApiOperation(value = "Delete the image by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image deleted with success"),
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
