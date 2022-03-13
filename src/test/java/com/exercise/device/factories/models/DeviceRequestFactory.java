package com.exercise.device.factories.models;

import com.exercise.device.factories.CommonFactory;
import com.exercise.device.models.DeviceModel;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter(lombok.AccessLevel.PRIVATE)
@Component
public class DeviceRequestFactory extends CommonFactory<DeviceModel> {

  private String name;
  private String brand;

  @Override
  protected DeviceModel build() {
    DeviceModel obj = new DeviceModel();
    obj.setName(getName());
    obj.setBrand(getBrand());

    return obj;
  }

  /**
   * @param aName the name to set
   */
  public DeviceRequestFactory setName(String aName) {
    name = aName;
    return this;
  }

  /**
   * @param brand the brand to set
   */
  public DeviceRequestFactory setBrand(String aBrand) {
    brand = aBrand;
    return this;
  }

}
