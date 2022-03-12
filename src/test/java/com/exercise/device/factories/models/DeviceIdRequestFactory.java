package com.exercise.device.factories.models;

import com.exercise.device.factories.CommonFactory;
import com.exercise.device.models.DeviceIdRequest;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter(lombok.AccessLevel.PRIVATE)
@Component
public class DeviceIdRequestFactory extends CommonFactory<DeviceIdRequest> {

  private Integer id;
  private String name;
  private String brand;

  @Override
  protected DeviceIdRequest build() {
    DeviceIdRequest obj = new DeviceIdRequest();
    obj.setName(getName());
    obj.setBrand(getBrand());
    obj.setId(getId());

    return obj;
  }

  /**
   * @param aId the id to set
   */
  public DeviceIdRequestFactory setId(Integer aId) {
    id = aId;
    return this;
  }

  /**
   * @param aName the name to set
   */
  public DeviceIdRequestFactory setName(String aName) {
    name = aName;
    return this;
  }

  /**
   * @param brand the brand to set
   */
  public DeviceIdRequestFactory setBrand(String aBrand) {
    brand = aBrand;
    return this;
  }
}
