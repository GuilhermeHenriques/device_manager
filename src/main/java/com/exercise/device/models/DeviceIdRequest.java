package com.exercise.device.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeviceIdRequest extends DeviceRequest {
  private Integer id;

  /**
   * Constrcutor
   * 
   * @param aId
   * @param aName
   * @param aBrand
   */
  public DeviceIdRequest(Integer aId, String aName, String aBrand) {
    super(aName, aBrand);
    setId(aId);
  }
}
