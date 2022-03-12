package com.exercise.device.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceException extends Exception {
  private int code;
  private String key;
  private String msg;

  /**
   * Default constructor
   */
  public DeviceException() {
    this(ExceptionEnum.UNKNOWN_ERROR);
  }

  /**
   * Constructor with enum
   * 
   * @param aException
   */
  public DeviceException(ExceptionEnum aException) {
    setCode(aException.getCode());
    setKey(aException.getKey());
    setMsg(aException.getMsg());
  }
}
