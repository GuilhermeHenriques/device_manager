package com.exercise.device.handlers;

import java.util.List;

import com.exercise.device.models.DeviceResponse;

public class GetDevices extends CommonHandler<Void, List<DeviceResponse>> {

  public GetDevices() {
    super(null);
  }

  @Override
  protected List<DeviceResponse> handleRequest(Void aInputs) {
    return null;
  }
}
