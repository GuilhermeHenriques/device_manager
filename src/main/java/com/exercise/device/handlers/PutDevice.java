package com.exercise.device.handlers;

import com.exercise.device.database.entities.Device;
import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.exceptions.ExceptionEnum;
import com.exercise.device.models.DeviceModel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PutDevice extends CommonHandler<DeviceModel, DeviceModel> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected DeviceModel handleRequest(DeviceModel aInput) throws DeviceException {
    validate(aInput);
    return deviceHandler.update(aInput);
  }

  /**
   * Validate inputs
   * 
   * @param aInput
   * @throws DeviceException
   */
  private void validate(DeviceModel aInput) throws DeviceException {
    checkId(aInput.getId());

    String brand = aInput.getBrand();
    String name = aInput.getName();

    if (!StringUtils.isBlank(brand) && brand.length() > Device.BRAND_SIZE) {
      throw new DeviceException(ExceptionEnum.INVALID_BRAND);
    }

    if (!StringUtils.isBlank(name) && name.length() > Device.NAME_SIZE) {
      throw new DeviceException(ExceptionEnum.INVALID_NAME);
    }
  }
}
