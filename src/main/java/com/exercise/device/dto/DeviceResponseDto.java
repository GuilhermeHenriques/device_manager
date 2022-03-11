package com.exercise.device.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceResponseDto extends DeviceRequestDto {
  private Integer id;
  private Date creation;
}
