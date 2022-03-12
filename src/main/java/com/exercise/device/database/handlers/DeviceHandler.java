package com.exercise.device.database.handlers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.exercise.device.database.entities.Device;
import com.exercise.device.database.mappers.DeviceMapper;
import com.exercise.device.database.repositories.IDeviceRepository;
import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.exceptions.ExceptionEnum;
import com.exercise.device.models.DeviceIdRequest;
import com.exercise.device.models.DeviceRequest;
import com.exercise.device.models.DeviceResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceHandler {
  /**
   * Logger
   */
  private static final Logger logger = LoggerFactory.getLogger(DeviceHandler.class);

  /**
   * Device repository
   */
  @Autowired
  private IDeviceRepository repo;

  /**
   * Create new device on db
   * 
   * @param aModel
   * @return
   */
  public DeviceResponse create(DeviceRequest aModel) {
    logger.info("Creating a new device");

    Device dev = new Device();
    dev.setBrand(aModel.getBrand());
    dev.setName(aModel.getName());
    dev.setCreation(new Date());
    dev = repo.save(dev);

    logger.info("Device has been created - id: \"" + dev.getId() + "\"");

    return DeviceMapper.device(dev);
  }

  /**
   * Update specific device info
   * 
   * @param aModel
   * @return
   * @throws DeviceException
   */
  public DeviceResponse update(DeviceIdRequest aModel) throws DeviceException {
    Device dev = findDevice(aModel.getId());

    if (!StringUtils.isBlank(aModel.getName())) {
      dev.setName(aModel.getName());
    }

    if (!StringUtils.isBlank(aModel.getBrand())) {
      dev.setBrand(aModel.getBrand());
    }

    dev = repo.save(dev);

    logger.info("Device has been updated");

    return DeviceMapper.device(dev);
  }

  /**
   * Find and return founded device
   * 
   * @param aId
   * @return
   * @throws DeviceException
   */
  public DeviceResponse find(Integer aId) throws DeviceException {
    Device dev = findDevice(aId);
    return DeviceMapper.device(dev);
  }

  /**
   * Delete specific device from database
   * 
   * @param aId
   * @throws DeviceException
   */
  public void delete(int aId) throws DeviceException {
    Device dev = findDevice(aId);
    repo.delete(dev);

    logger.info("Device has been deleted");
  }

  /**
   * List all devices
   * 
   * @return
   */
  public List<DeviceResponse> list(DeviceIdRequest aInputs) {
    logger.info("Getting device list");
    List<Device> list = repo.getAllByFilters(aInputs.getId(), aInputs.getBrand(), aInputs.getName());
    logger.info("Found \"" + list.size() + "\" results");

    return DeviceMapper.list(list);
  }

  /**
   * Find specific device in DB
   * 
   * @param aId
   * @return
   * @throws DeviceException
   */
  private Device findDevice(int aId) throws DeviceException {
    logger.info("Find device with id \"" + aId + "\"");

    Optional<Device> result = repo.findById(aId);

    if (!result.isPresent()) {
      throw new DeviceException(ExceptionEnum.DEVICE_NOT_FOUND);
    }

    logger.info("Device has been found");

    return result.get();
  }
}
