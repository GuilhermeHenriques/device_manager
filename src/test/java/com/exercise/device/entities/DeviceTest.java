package com.exercise.device.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import com.exercise.device.ApplicationTests;
import com.exercise.device.database.entities.Device;
import com.exercise.device.factories.entities.DeviceFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeviceTest extends ApplicationTests {
  @Autowired
  private DeviceFactory deviceFact;

  @Test
  public void deviceEntityTest() {
    Device dev = deviceFact.get();

    String name = dev.getName();
    String brand = dev.getBrand();
    Date creation = dev.getCreation();

    assertTrue(name.contains("device_name"));
    assertEquals(Device.NAME_SIZE, name.length());

    assertTrue(brand.contains("device_brand"));
    assertEquals(Device.BRAND_SIZE, brand.length());

    assertNotNull(creation);
  }
}
