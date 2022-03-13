package com.exercise.device.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.exercise.device.database.entities.Device;
import com.exercise.device.exceptions.ExceptionEnum;
import com.exercise.device.factories.entities.DeviceFactory;
import com.exercise.device.factories.models.DeviceRequestFactory;
import com.exercise.device.models.DeviceModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class DeviceControllerTest extends Request {
  @Autowired
  private DeviceFactory deviceFactory;

  @Autowired
  private DeviceRequestFactory deviceRequestFactory;

  @Test
  public void getDevice() {
    Device device = deviceFactory.get();

    Request response = get("/v1/device/" + device.getId());
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceModel responseObj = new Gson().fromJson(response.asString(), DeviceModel.class);
    assertEquals(device.getId(), responseObj.getId());
    assertEquals(device.getName(), responseObj.getName());
    assertEquals(device.getBrand(), responseObj.getBrand());
    assertEquals(device.getCreation().toString(), responseObj.getCreation().toString());

    response = get("/v1/device/0");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));

    response = get("/v1/device/0");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));
  }

  @Test
  public void getDevices() {
    List<Device> devicesDB = deviceFactory.get(5);

    Request response = get("/v1/devices");
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    List<DeviceModel> list = new Gson().fromJson(response.asString(), new TypeToken<List<DeviceModel>>() {
    }.getType());

    assertTrue(devicesDB.size() <= list.size());

    for (int i = 0; i < devicesDB.size(); i++) {
      Device deviceDB = devicesDB.get(i);
      DeviceModel responseObj = list.get(i);

      if (deviceDB.getId() != responseObj.getId()) {
        continue;
      }

      assertEquals(deviceDB.getBrand(), responseObj.getBrand());
      assertEquals(deviceDB.getName(), responseObj.getName());
      assertEquals(deviceDB.getCreation().toString(),
          responseObj.getCreation().toString());
    }
  }

  @Test
  public void postDevice() {
    DeviceModel request = deviceRequestFactory
        .setBrand("brand_post_test")
        .setName("name_post_test")
        .get();

    Request response = post("/v1/device", request);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceModel responseObj = new Gson().fromJson(response.asString(), DeviceModel.class);
    assertNotNull(responseObj.getId());
    assertEquals(request.getName(), responseObj.getName());
    assertEquals(request.getBrand(), responseObj.getBrand());
    assertNotNull(responseObj.getCreation());

    request = deviceRequestFactory
        .setBrand("brand_post_test")
        .setName(null)
        .get();

    response = post("/v1/device", request);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains("INVALID_NAME"));

    request = deviceRequestFactory
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

    DeviceModel request = deviceRequestFactory
        .setBrand("brand_put_test")
        .setName("name_put_test")
        .get();

    Request response = put("/v1/device/" + device.getId(), request);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    DeviceModel responseObj = new Gson().fromJson(response.asString(), DeviceModel.class);
    assertEquals(device.getId(), responseObj.getId());
    assertEquals(request.getName(), responseObj.getName());
    assertEquals(request.getBrand(), responseObj.getBrand());
    assertEquals(device.getCreation().toString(), responseObj.getCreation().toString());

    request = deviceRequestFactory.setBrand("brand_put_test")
        .setName(null)
        .get();

    response = put("/v1/device/" + device.getId(), request);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    request = deviceRequestFactory.setBrand(null)
        .setName("name_put_test")
        .get();

    response = put("/v1/device/" + device.getId(), request);
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    response = put("/v1/device/0", request);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));

    response = put("/v1/device/999999999", request);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.DEVICE_NOT_FOUND.getKey()));
  }

  @Test
  public void deleteDevice() {
    Device device = deviceFactory.get();

    Request response = delete("/v1/device/" + device.getId());
    assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    response = delete("/v1/device/0");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));

    response = delete("/v1/device/0");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponse().getStatus());
    assertTrue(response.asString().contains(ExceptionEnum.INVALID_ID.getKey()));
  }
}
