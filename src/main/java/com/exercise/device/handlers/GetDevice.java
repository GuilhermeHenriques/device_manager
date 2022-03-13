package com.exercise.device.handlers;

import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.models.DeviceModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDevice extends CommonHandler<Integer, DeviceModel> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected DeviceModel handleRequest(Integer aId) throws DeviceException {
    checkId(aId);
    return deviceHandler.find(aId);
  }
}
