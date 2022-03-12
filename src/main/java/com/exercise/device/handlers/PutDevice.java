package com.exercise.device.handlers;

import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.models.DeviceIdRequest;
import com.exercise.device.models.DeviceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PutDevice extends CommonHandler<DeviceIdRequest, DeviceResponse> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected DeviceResponse handleRequest(DeviceIdRequest aInput) throws DeviceException {
    checkId(aInput.getId());
    return deviceHandler.update(aInput);
  }
}
