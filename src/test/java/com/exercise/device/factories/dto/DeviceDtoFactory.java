package com.exercise.device.factories.dto;

import com.exercise.device.factories.CommonFactory;

import lombok.Getter;

@Getter(lombok.AccessLevel.PRIVATE)
public class DeviceDtoFactory extends CommonFactory<DeviceRequestDto> {

  private String name;
  private String brand;

  @Override
  protected DeviceRequestDto build() {
    DeviceRequestDto obj = new DeviceRequestDto();
    obj.setName(getName());
    obj.setBrand(getBrand());

    return obj;
  }

  /**
   * @param aName the name to set
   */
  public DeviceDtoFactory setName(String aName) {
    name = aName;
    return this;
  }

  /**
   * @param brand the brand to set
   */
  public DeviceDtoFactory setBrand(String aBrand) {
    brand = aBrand;
    return this;
  }

}
