package com.vesta.controller;

import com.vesta.controller.view.DeviceCreateView;
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
    DeviceView getById(@PathVariable("id") Long id);

    @ApiOperation(value = "Returns device by device name")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successfully found device with such name"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @ResponseBody
    @GetMapping("/{deviceName}")
    DeviceView getByDeviceName(@RequestParam(value = "deviceName") String deviceName);

    @ApiOperation(value = "Returns device by IP address")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found device with such IP address"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @ResponseBody
    @GetMapping("/{ipAddress}")
    DeviceView getByIpAddress(@RequestParam(value = "ipAddress") String ipAddress);

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
    DeviceCreateView update(@PathVariable("id") Long id, @RequestBody DeviceCreateView deviceView);

    @ApiOperation(value = "Create new device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Device successfully created"),
            @ApiResponse(code = 409, message = "This device already exists"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody DeviceCreateView deviceView);

    @ApiOperation(value = "Delete device by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Device was deleted successfully"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete device by device name")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Device was deleted successfully"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @DeleteMapping("/{deviceName}")
    void deleteByDeviceName(@RequestParam(value = "deviceName") String deviceName);

    @ApiOperation(value = "Delete device by IP address")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Device was deleted successfully"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    @DeleteMapping("/{ipAddress}")
    void deleteByIpAddress(@RequestParam(value = "ipAddress") String ipAddress);
}