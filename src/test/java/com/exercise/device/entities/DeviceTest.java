package com.exercise.device.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeviceTest extends ApplicationTests {
  @Autowired
  private DeviceFactory deviceFact;

  @Test
  public void deviceTest() {
    Device dev = deviceFact.get();

    String name = dev.getName();
    String brand = dev.getBrand();
    Date creation = dev.getCreation();

    assertTrue(name.contains("device_name"));
    assertTrue(brand.contains("decivce_brands"));
    assertNotNull(creation);
  }
}
