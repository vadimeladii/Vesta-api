package com.vesta.controller;

import com.vesta.controller.view.SubjectView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@Api(value = "Subject Controller REST Endpoint")
public interface SubjectController {

    @ApiOperation(value = "Delete by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subject deleted with success"),
            @ApiResponse(code = 404, message = "Subject not found"),
    })
    @DeleteMapping("/subjects/{id}")
    void delete(@PathVariable Long id);

    @ApiOperation(value = "Returns the image by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get subjects by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Subject not found")
    })
    @ResponseBody
    @GetMapping("/subjects/{id}")
    SubjectView getById(@PathVariable Long id);

    @ApiOperation(value = "Returns the all subjects")
    @GetMapping("/subjects/all")
    List<SubjectView> getAll();

    @ApiOperation(value = "Returns the all subjects by FloorID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get subjects by FlooID has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Floor not found")
    })
    @GetMapping("/subjects/all/by/floor")
    List<SubjectView> getAllByFloorId(Long floorId);

    @ApiOperation(value = "Add a new subject")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Subject was added"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/subjects/add/new/subject")
    @ResponseStatus(HttpStatus.CREATED)
    void addSubject(@RequestBody SubjectView subjectView);
}
