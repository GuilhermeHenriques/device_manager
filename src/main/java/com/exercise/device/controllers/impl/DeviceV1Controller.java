package com.exercise.device.controllers.impl;

import com.exercise.device.controllers.BaseController;
import com.exercise.device.controllers.IDeviceV1Controller;
import com.exercise.device.handlers.DeleteDevice;
import com.exercise.device.handlers.GetDevice;
import com.exercise.device.handlers.GetDevices;
import com.exercise.device.handlers.PostDevice;
import com.exercise.device.handlers.PutDevice;
import com.exercise.device.models.DeviceModel;

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
  public ResponseEntity<Object> postDevice(@RequestBody DeviceModel aBody) {
    return response(postDevice.execute(aBody));
  }

  @Override
  public ResponseEntity<Object> getDevice(@PathVariable("id") Integer aId) {
    return response(getDevice.execute(aId));
  }

  @Override
  public ResponseEntity<Object> putDevice(@PathVariable("id") Integer aId, @RequestBody DeviceModel aBody) {
    DeviceModel model = new DeviceModel(aId, aBody.getName(), aBody.getBrand(), null);
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
    DeviceModel inputs = new DeviceModel(aId, aName, aBrand, null);
    return response(getDevices.execute(inputs));
  }
}
