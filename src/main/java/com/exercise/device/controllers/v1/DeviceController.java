package com.exercise.device.controllers.v1;

import com.exercise.device.dto.DeviceRequestDto;
import com.exercise.device.handlers.DeleteDevice;
import com.exercise.device.handlers.GetDevice;
import com.exercise.device.handlers.GetDevices;
import com.exercise.device.handlers.PostDevice;
import com.exercise.device.handlers.PutDevice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController extends ApiV1 {
  @RequestMapping(value = "/v1/device", method = RequestMethod.POST)
  ResponseEntity<Object> postDevice(@RequestBody DeviceRequestDto aBody) {
    return response(new PostDevice(aBody));
  }

  @RequestMapping(value = "/v1/device/{id}", method = RequestMethod.GET)
  ResponseEntity<Object> getDevice(@PathVariable Integer id) {
    return response(new GetDevice(id));
  }

  @RequestMapping(value = "/v1/device/{id}", method = RequestMethod.PUT)
  ResponseEntity<Object> putDevice(@PathVariable Integer aId, @RequestBody DeviceRequestDto aBody) {
    return response(new PutDevice(aId, aBody));
  }

  @RequestMapping(value = "/v1/device/{id}", method = RequestMethod.DELETE)
  ResponseEntity<Object> deleteDevice(@PathVariable Integer aId) {
    return response(new DeleteDevice(aId));
  }

  @RequestMapping(value = "/v1/devices", method = RequestMethod.GET)
  ResponseEntity<Object> getDevices() {
    return response(new GetDevices());
  }
}
