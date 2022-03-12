package com.exercise.device.handlers;

import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.exceptions.DeviceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDevice extends CommonHandler<Integer, Void> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected Void handleRequest(Integer aDeviceId) throws DeviceException {
    checkId(aDeviceId);
    deviceHandler.delete(aDeviceId);

    return null;
  }
}
