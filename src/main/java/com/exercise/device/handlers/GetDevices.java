package com.exercise.device.handlers;

import java.util.List;

import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.models.DeviceModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDevices extends CommonHandler<DeviceModel, List<DeviceModel>> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected List<DeviceModel> handleRequest(DeviceModel aInputs) throws Exception {
    return deviceHandler.list(aInputs);
  }
}
