package com.exercise.device.factories;

import org.springframework.stereotype.Component;

@Component
public class DeviceFactory extends CommonFactory<Device> {

  /**
   * @param aRepo
   */
  public DeviceFactory(DeviceRepository aRepo) {
    super(aRepo);
  }

  @Override
  protected Device build() {
    Device obj = new Device();
    obj.setName();
    obj.setBrand();

    return obj;
  }

}
