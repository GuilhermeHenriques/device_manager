package com.exercise.device.handlers;

import com.exercise.device.models.DeviceRequest;
import com.exercise.device.models.DeviceResponse;

public class PostDevice extends CommonHandler<DeviceRequest, DeviceResponse> {

  public PostDevice(DeviceRequest aInput) {
    super(aInput);
  }

  @Override
  protected DeviceResponse handleRequest(DeviceRequest aInput) {
    return null;
  }
}
