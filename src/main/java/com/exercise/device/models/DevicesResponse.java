package com.exercise.device.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DevicesResponse {
  private List<DeviceResponse> devices;
}
