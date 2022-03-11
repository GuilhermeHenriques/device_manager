package com.exercise.device.integration.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.exercise.device.database.entities.Device;
import com.exercise.device.factories.dto.DeviceDtoFactory;
import com.exercise.device.factories.entities.DeviceFactory;
import com.exercise.device.integration.Request;
import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class DeviceControllerTest extends Request {
  @Autowired
  private DeviceFactory deviceFactory;

  @Autowired
  private DeviceDtoFactory deviceDtoFactory;

  @Test
  public void getDevice() {
    Device device = deviceFactory.get();

    Request response = get("/v1/device/" + device.getId());
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceRequestDto deviceDto = new Gson().fromJson(response.asString(), DeviceRequestDto.class);
    assertEquals(device.getId(), deviceDto.getId());
    assertEquals(device.getName(), deviceDto.getName());
    assertEquals(device.getBrand(), deviceDto.getBrand());
    assertEquals(device.getCreation().toString(), deviceDto.getCreation().toString());
  }

  @Test
  public void getDevices() {
    List<Device> devices = deviceFactory.get(5);

    Request response = get("/v1/devices");
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
  }

  @Test
  public void postDevice() {
    DeviceRequestDto requestDto = deviceDtoFactory
        .setBrand("brand_post_test")
        .setName("name_post_test")
        .get();

    Device device = deviceFactory.get();

    Request response = post("/v1/device", requestDto);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceResponseDto responseDto = new Gson().fromJson(response.asString(), DeviceResponseDto.class);
    assertNotNull(responseDto.getId());
    assertEquals(requestDto.getName(), responseDto.getName());
    assertEquals(requestDto.getBrand(), responseDto.getBrand());
    assertNotNull(responseDto.getCreation());
  }

  @Test
  public void putDevice() {
    Device device = deviceFactory.get();

    DeviceRequestDto requestDto = deviceDtoFactory
        .setBrand("brand_put_test")
        .setName("name_put_test")
        .get();

    Request response = put("/v1/device/" + device.getId(), requestDto);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceResponseDto responseDto = new Gson().fromJson(response.asString(), DeviceResponseDto.class);
    assertEquals(device.getId(), responseDto.getId());
    assertEquals(requestDto.getName(), responseDto.getName());
    assertEquals(requestDto.getBrand(), responseDto.getBrand());
    assertEquals(device.getCreation().toString(), responseDto.getCreation().toString());
  }

  @Test
  public void deleteDevice() {
    Device device = deviceFactory.get();

    Request response = delete("/v1/device/" + device.getId());
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
  }
}
