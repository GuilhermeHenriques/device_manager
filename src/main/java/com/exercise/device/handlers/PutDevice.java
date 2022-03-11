package com.exercise.device.handlers;

import com.exercise.device.models.DeviceRequest;
import com.exercise.device.models.DeviceResponse;

import lombok.Getter;
import lombok.Setter;

@Setter(lombok.AccessLevel.PRIVATE)
@Getter(lombok.AccessLevel.PRIVATE)
public class PutDevice extends CommonHandler<DeviceRequest, DeviceResponse> {

  private int deviceId;

  public PutDevice(int aDeviceId, DeviceRequest aInput) {
    super(aInput);
    setDeviceId(aDeviceId);
  }

  @Override
  protected DeviceResponse handleRequest(DeviceRequest aInput) {
    return null;
  }
}
