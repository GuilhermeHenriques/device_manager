package com.exercise.device.factories;

import java.util.Date;

import com.exercise.device.database.entities.Device;
import com.exercise.device.database.repositories.DeviceRepository;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter(value = lombok.AccessLevel.PRIVATE)
@Getter(value = lombok.AccessLevel.PRIVATE)
public class DeviceFactory extends CommonFactory<Device> {

  private String name;
  private String brand;

  /**
   * @param aRepo
   */
  public DeviceFactory(DeviceRepository aRepo) {
    super(aRepo);

    String baseName = "device_name_";
    String baseBrand = "device_brand_";

    setName(baseName + generateRandomStr(Device.NAME_SIZE - baseName.length()));
    setBrand(baseBrand + generateRandomStr(Device.BRAND_SIZE - baseBrand.length()));
  }

  @Override
  protected Device build() {
    Device obj = new Device();
    obj.setName(getName());
    obj.setBrand(getBrand());
    obj.setCreation(new Date());

    return obj;
  }
}
