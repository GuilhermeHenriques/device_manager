package com.exercise.device.handlers;

import java.util.List;

import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.models.DeviceIdRequest;
import com.exercise.device.models.DeviceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDevices extends CommonHandler<DeviceIdRequest, List<DeviceResponse>> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected List<DeviceResponse> handleRequest(DeviceIdRequest aInputs) throws Exception {
    return deviceHandler.list(aInputs);
  }
}
