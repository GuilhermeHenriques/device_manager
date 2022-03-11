package com.exercise.device.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceResponse extends DeviceRequest {
  private Integer id;
  private Date creation;
}
