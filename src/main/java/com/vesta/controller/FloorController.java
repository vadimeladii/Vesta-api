package com.vesta.controller;

import com.vesta.controller.view.FloorView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/floors")
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

}