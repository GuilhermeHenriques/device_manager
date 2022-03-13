package com.exercise.device.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceModel {
  private Integer id;
  private String name;
  private String brand;
  private Date creation;
}
