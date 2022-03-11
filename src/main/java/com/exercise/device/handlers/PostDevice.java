package com.exercise.device.handlers;

import com.exercise.device.dto.DeviceRequestDto;
import com.exercise.device.dto.DeviceResponseDto;

public class PostDevice extends CommonHandler<DeviceRequestDto, DeviceResponseDto> {

  public PostDevice(DeviceRequestDto aInput) {
    super(aInput);
  }

  @Override
  protected DeviceResponseDto handleRequest(DeviceRequestDto aInput) {
    return null;
  }
}
