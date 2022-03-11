package com.exercise.device.handlers;

import com.exercise.device.dto.DeviceRequestDto;
import com.exercise.device.dto.DeviceResponseDto;

import lombok.Getter;
import lombok.Setter;

@Setter(lombok.AccessLevel.PRIVATE)
@Getter(lombok.AccessLevel.PRIVATE)
public class PutDevice extends CommonHandler<DeviceRequestDto, DeviceResponseDto> {

  private int deviceId;

  public PutDevice(int aDeviceId, DeviceRequestDto aInput) {
    super(aInput);
    setDeviceId(aDeviceId);
  }

  @Override
  protected DeviceResponseDto handleRequest(DeviceRequestDto aInput) {
    return null;
  }
}
