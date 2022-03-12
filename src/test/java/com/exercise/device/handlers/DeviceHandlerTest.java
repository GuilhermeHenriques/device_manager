package com.exercise.device.handlers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.exercise.device.ApplicationTests;
import com.exercise.device.database.entities.Device;
import com.exercise.device.exceptions.ExceptionEnum;
import com.exercise.device.factories.entities.DeviceFactory;
import com.exercise.device.factories.models.DeviceIdRequestFactory;
import com.exercise.device.factories.models.DeviceRequestFactory;
import com.exercise.device.models.DeviceIdRequest;
import com.exercise.device.models.DeviceRequest;
import com.exercise.device.models.DeviceResponse;
import com.exercise.device.models.ExceptionResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class DeviceHandlerTest extends ApplicationTests {
  @Autowired
  private DeviceFactory devFact;

  @Autowired
  private DeviceRequestFactory devReqFac;

  @Autowired
  private DeviceIdRequestFactory devIdReqFac;

  @Autowired
  private PostDevice postDevice;

  @Autowired
  private PutDevice putDevice;

  @Autowired
  private GetDevice getDevice;

  @Autowired
  private DeleteDevice deleteDevice;

  @Autowired
  private GetDevices getDevices;

  @Test
  public void postDeviceSuccessTest() {
    DeviceRequest inputs = devReqFac.setName("test_name")
        .setBrand("brand_name")
        .get();

    PostDevice handler = (PostDevice) assertDoesNotThrow(() -> postDevice.execute(inputs));
    assertEquals(HttpStatus.OK, handler.getStatus());

    DeviceResponse response = (DeviceResponse) handler.getOutput();
    assertNotNull(response.getId());
    assertEquals(response.getName(), inputs.getName());
    assertEquals(response.getBrand(), inputs.getBrand());
    assertNotNull(response.getCreation());
  }

  @Test
  public void postDeviceFailTest() {
    DeviceRequest inputs = devReqFac.setName(null)
        .setBrand(null)
        .get();

    PostDevice handler = (PostDevice) assertDoesNotThrow(() -> postDevice.execute(inputs));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
  }

  @Test
  public void putDeviceSuccessTest() {
    Device dev = devFact.get();

    DeviceIdRequest inputs = devIdReqFac.setName("test_name")
        .setBrand("brand_name")
        .setId(dev.getId())
        .get();

    PutDevice handler = (PutDevice) assertDoesNotThrow(() -> putDevice.execute(inputs));
    assertEquals(HttpStatus.OK, handler.getStatus());

    DeviceResponse response = (DeviceResponse) handler.getOutput();
    assertNotNull(response.getId());
    assertEquals(inputs.getName(), response.getName());
    assertEquals(inputs.getBrand(), response.getBrand());
    assertNotNull(response.getCreation());

    DeviceIdRequest inputs2 = devIdReqFac.setName("test_name_3232")
        .setId(dev.getId())
        .get();

    handler = (PutDevice) assertDoesNotThrow(() -> putDevice.execute(inputs2));
    assertEquals(HttpStatus.OK, handler.getStatus());

    DeviceResponse response2 = (DeviceResponse) handler.getOutput();
    assertEquals("test_name_3232", response2.getName());
    assertEquals(response.getBrand(), response2.getBrand());
    assertEquals(response.getCreation(), response2.getCreation());
    assertEquals(response.getId(), response2.getId());

    DeviceIdRequest inputs3 = devIdReqFac.setBrand("brand_name_3232")
        .setId(dev.getId())
        .get();

    handler = (PutDevice) assertDoesNotThrow(() -> putDevice.execute(inputs3));
    assertEquals(HttpStatus.OK, handler.getStatus());

    DeviceResponse response3 = (DeviceResponse) handler.getOutput();
    assertEquals(response2.getName(), response3.getName());
    assertEquals("brand_name_3232", response3.getBrand());
    assertEquals(response2.getCreation(), response3.getCreation());
    assertEquals(response2.getId(), response3.getId());
  }

  @Test
  public void putDeviceFailTest() {
    DeviceIdRequest inputs = devIdReqFac.setName(null)
        .setBrand(null)
        .setId(null)
        .get();

    PutDevice handler = (PutDevice) assertDoesNotThrow(() -> putDevice.execute(inputs));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    ExceptionResponse obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.INVALID_ID.getKey(), obj.getKey());

    DeviceIdRequest inputs2 = devIdReqFac.setName(null)
        .setBrand(null)
        .setId(0)
        .get();

    handler = (PutDevice) assertDoesNotThrow(() -> putDevice.execute(inputs2));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.INVALID_ID.getKey(), obj.getKey());

    DeviceIdRequest inputs3 = devIdReqFac.setName(null)
        .setBrand(null)
        .setId(999999)
        .get();

    handler = (PutDevice) assertDoesNotThrow(() -> putDevice.execute(inputs3));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.DEVICE_NOT_FOUND.getKey(), obj.getKey());
  }

  @Test
  public void getDeviceSuccessTest() {
    Device dev = devFact.get();

    GetDevice handler = (GetDevice) assertDoesNotThrow(() -> getDevice.execute(dev.getId()));
    assertEquals(HttpStatus.OK, handler.getStatus());

    DeviceResponse response = (DeviceResponse) handler.getOutput();
    assertEquals(dev.getName(), response.getName());
    assertEquals(dev.getBrand(), response.getBrand());
    assertEquals(dev.getCreation(), response.getCreation());
    assertEquals(dev.getId(), response.getId());
  }

  @Test
  public void getDeviceFailTest() {
    GetDevice handler = (GetDevice) assertDoesNotThrow(() -> getDevice.execute(0));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    ExceptionResponse obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.INVALID_ID.getKey(), obj.getKey());

    handler = (GetDevice) assertDoesNotThrow(() -> getDevice.execute(99999));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.DEVICE_NOT_FOUND.getKey(), obj.getKey());

    handler = (GetDevice) assertDoesNotThrow(() -> getDevice.execute(null));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.INVALID_ID.getKey(), obj.getKey());
  }

  @Test
  public void deleteDeviceSuccessTest() {
    Device dev = devFact.get();

    DeleteDevice handler = (DeleteDevice) assertDoesNotThrow(() -> deleteDevice.execute(dev.getId()));
    assertEquals(HttpStatus.OK, handler.getStatus());
  }

  @Test
  public void deleteDeviceFailTest() {
    DeleteDevice handler = (DeleteDevice) assertDoesNotThrow(() -> deleteDevice.execute(-1));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    ExceptionResponse obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.INVALID_ID.getKey(), obj.getKey());

    handler = (DeleteDevice) assertDoesNotThrow(() -> deleteDevice.execute(999999));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.DEVICE_NOT_FOUND.getKey(), obj.getKey());

    handler = (DeleteDevice) assertDoesNotThrow(() -> deleteDevice.execute(null));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.getStatus());
    obj = (ExceptionResponse) handler.getOutput();
    assertEquals(ExceptionEnum.INVALID_ID.getKey(), obj.getKey());
  }

  @Test
  public void getDevicesSuccessTest() {
    devFact.get(5);

    DeviceIdRequest inputs = devIdReqFac.setName(null)
        .setBrand(null)
        .setId(null)
        .get();

    GetDevices handler = (GetDevices) assertDoesNotThrow(() -> getDevices.execute(inputs));
    assertEquals(HttpStatus.OK, handler.getStatus());
  }
}
