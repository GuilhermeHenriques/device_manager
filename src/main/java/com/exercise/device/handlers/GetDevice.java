package com.exercise.device.handlers;

import com.exercise.device.dto.DeviceResponseDto;

public class GetDevice extends CommonHandler<Integer, DeviceResponseDto> {

  public GetDevice(Integer aInput) {
    super(aInput);
  }

  @Override
  protected DeviceResponseDto handleRequest(Integer aInput) {
    return null;
  }
}
