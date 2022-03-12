package com.exercise.device.handlers;

import com.exercise.device.database.handlers.DeviceHandler;
import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.exceptions.ExceptionEnum;
import com.exercise.device.models.DeviceRequest;
import com.exercise.device.models.DeviceResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDevice extends CommonHandler<DeviceRequest, DeviceResponse> {
  @Autowired
  private DeviceHandler deviceHandler;

  @Override
  protected DeviceResponse handleRequest(DeviceRequest aInput) throws Exception {
    validate(aInput);

    return deviceHandler.create(aInput);
  }

  /**
   * Validate inputs
   * 
   * @param aInput
   * @throws DeviceException
   */
  private void validate(DeviceRequest aInput) throws DeviceException {
    String brand = aInput.getBrand();
    String name = aInput.getName();

    if (StringUtils.isBlank(brand)) {
      throw new DeviceException(ExceptionEnum.INVALID_BRAND);
    }

    if (StringUtils.isBlank(name)) {
      throw new DeviceException(ExceptionEnum.INVALID_NAME);
    }
  }
}
