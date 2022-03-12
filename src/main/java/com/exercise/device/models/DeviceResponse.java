package com.exercise.device.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceResponse extends DeviceIdRequest {
  private Date creation;

  /**
   * Constrcutor
   * 
   * @param aId
   * @param aCreation
   */
  public DeviceResponse(Integer aId, String aName, String aBrand, Date aCreation) {
    super(aId, aName, aBrand);
    setCreation(aCreation);
  }

}
