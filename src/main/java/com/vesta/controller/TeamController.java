package com.vesta.controller;

import com.vesta.controller.view.TeamCreateView;
import com.vesta.controller.view.TeamView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/team")
@Api(value = "Team Controller REST Endpoint")
public interface TeamController {

    @ApiOperation(value = "Returns the team by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get team by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Team not found")
    })
    @ResponseBody
    @GetMapping("/{id}")
    TeamView getById(@PathVariable Long id);

    @ApiOperation(value = "Returns all teams in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all teamss has succeeded"),
            @ApiResponse(code = 404, message = "Team not found")
    })
    @ResponseBody
    @GetMapping
    List<TeamView> findAll();

    @ApiOperation(value = "Modify team data by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Team was updated successfully"),
            @ApiResponse(code = 404, message = "Team not found")
    })
    @PutMapping("/{id}")
    TeamCreateView update(@PathVariable("id") Long id, @RequestBody TeamCreateView teamCreateView);

    @ApiOperation(value = "Create a new team")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Team was created"),
            @ApiResponse(code = 409, message = "Conflict team already exists"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody TeamCreateView teamCreateView);

    @ApiOperation(value = "Delete team by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Team was deleted successfully"),
            @ApiResponse(code = 404, message = "Team not found")
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}