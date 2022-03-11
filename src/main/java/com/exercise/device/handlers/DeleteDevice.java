package com.exercise.device.handlers;

public class DeleteDevice extends CommonHandler<Integer, Void> {

  public DeleteDevice(Integer aDeviceId) {
    super(aDeviceId);
  }

  @Override
  protected Void handleRequest(Integer aDeviceId) {
    return null;
  }
}
