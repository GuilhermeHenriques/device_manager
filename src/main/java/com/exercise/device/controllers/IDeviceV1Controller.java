package com.exercise.device.controllers;

import javax.ws.rs.core.MediaType;

import com.exercise.device.models.DeviceRequest;
import com.exercise.device.models.DeviceResponse;
import com.exercise.device.models.ExceptionResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("v1")
@Api(value = "deviceController", tags = { "device" }, basePath = "v1", description = "manually built controller")
public interface IDeviceV1Controller {
  @ApiOperation(value = "Add new device in DB", tags = { "device" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "New device object had been added on DB", response = DeviceResponse.class),
      @ApiResponse(code = 500, message = "Service exception. Can be Runtime Java exception or a DeviceException", response = ExceptionResponse.class)
  })
  @RequestMapping(value = "/device", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity<Object> postDevice(@RequestBody DeviceRequest aBody);

  @ApiOperation(value = "Get specific device from DB", tags = { "device" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Device had been found", response = DeviceResponse.class),
      @ApiResponse(code = 500, message = "Service exception. Can be Runtime Java exception or a DeviceException", response = ExceptionResponse.class) })
  @RequestMapping(value = "/device/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity<Object> getDevice(@PathVariable("id") Integer aId);

  @ApiOperation(value = "Update specific device", tags = { "device" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Device had been updated", response = DeviceResponse.class),
      @ApiResponse(code = 500, message = "Service exception. Can be Runtime Java exception or a DeviceException", response = ExceptionResponse.class) })
  @RequestMapping(value = "/device/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity<Object> putDevice(@PathVariable("id") Integer aId, @RequestBody DeviceRequest aBody);

  @ApiOperation(value = "Delete device from DB", tags = { "device" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "New device object had been added on DB", response = Void.class),
      @ApiResponse(code = 500, message = "Service exception. Can be Runtime Java exception or a DeviceException", response = ExceptionResponse.class)
  })
  @RequestMapping(value = "/device/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity<Object> deleteDevice(@PathVariable("id") Integer aId);

  @ApiOperation(value = "Get device list from DB", tags = { "device" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "List of devices", response = DeviceResponse.class),
      @ApiResponse(code = 500, message = "Service exception. Can be Runtime Java exception or a DeviceException", response = ExceptionResponse.class) })
  @RequestMapping(value = "/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity<Object> getDevices(@RequestParam(name = "id", required = false) Integer aId,
      @RequestParam(name = "name", required = false) String aName,
      @RequestParam(name = "brand", required = false) String aBrand);
}
