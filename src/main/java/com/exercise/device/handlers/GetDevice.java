package com.exercise.device.handlers;

import com.exercise.device.models.DeviceResponse;

public class GetDevice extends CommonHandler<Integer, DeviceResponse> {

  public GetDevice(Integer aInput) {
    super(aInput);
  }

  @Override
  protected DeviceResponse handleRequest(Integer aInput) {
    return null;
  }
}
