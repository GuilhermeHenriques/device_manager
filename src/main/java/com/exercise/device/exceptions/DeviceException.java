package com.exercise.device.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceException extends Exception {
  private final int DEFAULT_ERROR_CODE = 999;
  private final String DEFAULT_ERROR_KEY = "RUNTIME_EXCEPTION";
  private final int code;
  private final String key;

  private String msg = "";

  /**
   * Default constructor
   */
  public DeviceException() {
    code = DEFAULT_ERROR_CODE;
    key = DEFAULT_ERROR_KEY;
  }

  /**
   * Constructor with enum
   * 
   * @param aException
   */
  public DeviceException(ExceptionEnum aException) {
    code = aException.getCode();
    key = aException.getKey();
    msg = aException.getMsg();
  }
}
