package com.exercise.device.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.exercise.device.database.entities.Device;
import com.exercise.device.exceptions.ExceptionEnum;
import com.exercise.device.factories.entities.DeviceFactory;
import com.exercise.device.factories.models.DeviceRequestFactory;
import com.exercise.device.models.DeviceRequest;
import com.exercise.device.models.DeviceResponse;
import com.exercise.device.models.DevicesResponse;
import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class DeviceControllerTest extends Request {
  @Autowired
  private DeviceFactory deviceFactory;

  @Autowired
  private DeviceRequestFactory deviceDtoFactory;

  @Test
  public void getDevice() {
    Device device = deviceFactory.get();

    Request response = get("/v1/device/" + device.getId());
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceResponse deviceDto = new Gson().fromJson(response.asString(), DeviceResponse.class);
    assertEquals(device.getId(), deviceDto.getId());
    assertEquals(device.getName(), deviceDto.getName());
    assertEquals(device.getBrand(), deviceDto.getBrand());
    assertEquals(device.getCreation().toString(), deviceDto.getCreation().toString());

    response = get("/v1/device/0");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));
  }

  @Test
  public void getDevices() {
    List<Device> devicesDB = deviceFactory.get(5);

    Request response = get("/v1/devices");
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DevicesResponse devicesDto = new Gson().fromJson(response.asString(),
        DevicesResponse.class);
    List<DeviceResponse> list = devicesDto.getDevices();

    assertEquals(devicesDB.size(), list.size());

    for (int i = 0; i < devicesDB.size(); i++) {
      Device deviceDB = devicesDB.get(i);
      DeviceResponse deviceDto = list.get(i);

      assertEquals(deviceDB.getId(), deviceDto.getId());
      assertEquals(deviceDB.getBrand(), deviceDto.getBrand());
      assertEquals(deviceDB.getName(), deviceDto.getName());
      assertEquals(deviceDB.getCreation().toString(),
          deviceDto.getCreation().toString());
    }
  }

  @Test
  public void postDevice() {
    DeviceRequest request = deviceDtoFactory
        .setBrand("brand_post_test")
        .setName("name_post_test")
        .get();

    Request response = post("/v1/device", request);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceResponse responseDto = new Gson().fromJson(response.asString(), DeviceResponse.class);
    assertNotNull(responseDto.getId());
    assertEquals(request.getName(), responseDto.getName());
    assertEquals(request.getBrand(), responseDto.getBrand());
    assertNotNull(responseDto.getCreation());

    request = deviceDtoFactory
        .setBrand("brand_post_test")
        .setName(null)
        .get();

    response = post("/v1/device", request);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains("INVALID_NAME"));

    request = deviceDtoFactory
        .setBrand("")
        .setName("name_post_test")
        .get();

    response = post("/v1/device", request);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains("INVALID_BRAND"));
  }

  @Test
  public void putDevice() {
    Device device = deviceFactory.get();

    DeviceRequest request = deviceDtoFactory
        .setBrand("brand_put_test")
        .setName("name_put_test")
        .get();

    Request response = put("/v1/device/" + device.getId(), request);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceResponse responseDto = new Gson().fromJson(response.asString(), DeviceResponse.class);
    assertEquals(device.getId(), responseDto.getId());
    assertEquals(request.getName(), responseDto.getName());
    assertEquals(request.getBrand(), responseDto.getBrand());
    assertEquals(device.getCreation().toString(), responseDto.getCreation().toString());

    request = deviceDtoFactory.setBrand("brand_put_test")
        .setName(null)
        .get();

    response = put("/v1/device/" + device.getId(), request);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    response = put("/v1/device/0", request);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));
  }

  @Test
  public void deleteDevice() {
    Device device = deviceFactory.get();

    Request response = delete("/v1/device/" + device.getId());
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    response = delete("/v1/device/0");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));
  }
}
