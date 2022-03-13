package com.exercise.device.database.mappers;

import java.util.ArrayList;
import java.util.List;

import com.exercise.device.database.entities.Device;
import com.exercise.device.models.DeviceModel;

public class DeviceMapper {
  /**
   * Map entity device to DeviceRequest
   * 
   * @param aEntity
   * @return
   */
  public static DeviceModel device(Device aEntity) {
    if (aEntity == null) {
      return null;
    }

    return new DeviceModel(aEntity.getId(), aEntity.getName(), aEntity.getBrand(), aEntity.getCreation());
  }

  /**
   * Create list of device models
   * 
   * @param aEntities
   * @return
   */
  public static List<DeviceModel> list(List<Device> aEntities) {
    List<DeviceModel> result = new ArrayList<DeviceModel>();

    if (aEntities == null || aEntities.size() <= 0) {
      return result;
    }

    aEntities.stream().forEach(deviceDB -> {
      result.add(device(deviceDB));
    });

    return result;
  }
}
