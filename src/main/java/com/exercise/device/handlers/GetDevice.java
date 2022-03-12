package com.exercise.device.handlers;

import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.models.DeviceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDevice extends CommonHandler<Integer, DeviceResponse> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected DeviceResponse handleRequest(Integer aId) throws DeviceException {
    checkId(aId);
    return deviceHandler.find(aId);
  }
}
