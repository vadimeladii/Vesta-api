package com.vesta.controller;

import com.vesta.controller.view.DeviceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/device")
@Api(value = "Device controller REST")
public interface DeviceController {

    @ApiOperation(value = "Returns device by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Getting info about device succeeded"),
            @ApiResponse(code = 403, message = "Access to the resource is forbidden"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @ResponseBody
    @GetMapping("/{id}")
    DeviceView getById(@PathVariable Long id);

    @ApiOperation(value = "Returns all devices in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All device were successfully requested"),
            @ApiResponse(code = 404, message = "Devices not found")
    })
    @ResponseBody
    @GetMapping
    List<DeviceView> findAll();

    @ApiOperation(value = "Update device info by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessfully updated device info"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @PutMapping("/{id}")
    DeviceView update(@PathVariable("id") Long id, @RequestBody DeviceView deviceView);

    @ApiOperation(value = "Create new device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Device successfully created"),
            @ApiResponse(code = 409, message = "This device already exists"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody DeviceView deviceView);

    @ApiOperation(value = "Delete device by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Device was deleted successfully"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
