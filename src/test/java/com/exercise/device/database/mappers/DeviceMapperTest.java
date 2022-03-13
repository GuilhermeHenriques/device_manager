package com.exercise.device.database.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import com.exercise.device.ApplicationTests;
import com.exercise.device.database.entities.Device;
import com.exercise.device.factories.entities.DeviceFactory;
import com.exercise.device.models.DeviceModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeviceMapperTest extends ApplicationTests {
  @Autowired
  private DeviceFactory factory;

  @Test
  public void deviceTest() {
    Device dev = factory.get();

    assertNull(DeviceMapper.device(null));

    DeviceModel response = DeviceMapper.device(dev);
    assertNotNull(response);
    assertEquals(dev.getId(), response.getId());
    assertEquals(dev.getName(), response.getName());
    assertEquals(dev.getBrand(), response.getBrand());
    assertEquals(dev.getCreation(), response.getCreation());
  }

  @Test
  public void listTest() {
    List<Device> dev = factory.get(5);

    List<DeviceModel> response = DeviceMapper.list(null);
    assertEquals(0, response.size());

    response = DeviceMapper.list(new ArrayList<Device>());
    assertEquals(0, response.size());

    response = DeviceMapper.list(dev);

    for (int i = 0; i < response.size(); i++) {
      assertEquals(dev.get(i).getId(), response.get(i).getId());
      assertEquals(dev.get(i).getName(), response.get(i).getName());
      assertEquals(dev.get(i).getBrand(), response.get(i).getBrand());
      assertEquals(dev.get(i).getCreation(), response.get(i).getCreation());
    }
  }
}
