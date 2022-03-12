package com.exercise.device.factories.entities;

import java.util.Date;

import com.exercise.device.database.entities.Device;
import com.exercise.device.database.repositories.IDeviceRepository;
import com.exercise.device.factories.CommonFactory;

import org.springframework.stereotype.Component;

@Component
public class DeviceFactory extends CommonFactory<Device> {

  private String name;
  private String brand;

  /**
   * @param aRepo
   */
  public DeviceFactory(IDeviceRepository aRepo) {
    super(aRepo);
  }

  @Override
  protected Device build() {
    String baseName = "device_name_";
    String baseBrand = "device_brand_";

    name = baseName + generateRandomStr(Device.NAME_SIZE - baseName.length());
    brand = baseBrand + generateRandomStr(Device.BRAND_SIZE - baseBrand.length());

    Device obj = new Device();
    obj.setName(name);
    obj.setBrand(brand);
    obj.setCreation(new Date());

    return obj;
  }

}
