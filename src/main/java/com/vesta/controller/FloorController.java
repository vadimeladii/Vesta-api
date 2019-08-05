package com.vesta.controller;

import com.vesta.controller.view.FloorView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/floor")
@Api(value = "Floor Controller REST Endpoint")
public interface FloorController {

    @ApiOperation(value = "Returns all floors in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all floors has succeeded"),
            @ApiResponse(code = 404, message = "Floors not found")
    })
    @ResponseBody
    @GetMapping
    List<FloorView> findAll();

    @ApiOperation(value = "Returns the floor by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get floor by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Floor not found")
    })

    @ResponseBody
    @GetMapping("/{id}")
    FloorView getById(@PathVariable Long id);

    @ApiOperation(value = "Create a new floor")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Floor was created"),
            @ApiResponse(code = 409, message = "Conflict, floor already exist"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody FloorView floorView);

    @ApiOperation(value = "Delete floor by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Floor was deleted successfully"),
            @ApiResponse(code = 404, message = "Floor not found")
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}