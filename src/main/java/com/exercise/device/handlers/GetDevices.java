package com.exercise.device.handlers;

import java.util.List;

import com.exercise.device.dto.DeviceResponseDto;

public class GetDevices extends CommonHandler<Void, List<DeviceResponseDto>> {

  public GetDevices() {
    super(null);
  }

  @Override
  protected List<DeviceResponseDto> handleRequest(Void aInputs) {
    return null;
  }
}
