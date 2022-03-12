package com.exercise.device.controllers.impl;

import com.exercise.device.controllers.BaseController;
import com.exercise.device.controllers.IDeviceV1Controller;
import com.exercise.device.handlers.DeleteDevice;
import com.exercise.device.handlers.GetDevice;
import com.exercise.device.handlers.GetDevices;
import com.exercise.device.handlers.PostDevice;
import com.exercise.device.handlers.PutDevice;
import com.exercise.device.models.DeviceIdRequest;
import com.exercise.device.models.DeviceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceV1Controller extends BaseController implements IDeviceV1Controller {
  @Autowired
  private PostDevice postDevice;

  @Autowired
  private GetDevice getDevice;

  @Autowired
  private PutDevice putDevice;

  @Autowired
  private DeleteDevice deleteDevice;

  @Autowired
  private GetDevices getDevices;

  @Override
  public ResponseEntity<Object> postDevice(@RequestBody DeviceRequest aBody) {
    return response(postDevice.execute(aBody));
  }

  @Override
  public ResponseEntity<Object> getDevice(@PathVariable("id") Integer aId) {
    return response(getDevice.execute(aId));
  }

  @Override
  public ResponseEntity<Object> putDevice(@PathVariable("id") Integer aId, @RequestBody DeviceRequest aBody) {
    DeviceIdRequest model = new DeviceIdRequest(aId, aBody.getName(), aBody.getBrand());
    return response(putDevice.execute(model));
  }

  @Override
  public ResponseEntity<Object> deleteDevice(@PathVariable("id") Integer aId) {
    return response(deleteDevice.execute(aId));
  }

  @Override
  public ResponseEntity<Object> getDevices(@RequestParam(name = "id", required = false) Integer aId,
      @RequestParam(name = "name", required = false) String aName,
      @RequestParam(name = "brand", required = false) String aBrand) {
    DeviceIdRequest inputs = new DeviceIdRequest(aId, aName, aBrand);
    return response(getDevices.execute(inputs));
  }
}
