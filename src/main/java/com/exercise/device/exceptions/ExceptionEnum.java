package com.exercise.device.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
  INVALID_NAME(301, "INVALID_NAME", "Please set a valid name"),
  INVALID_BRAND(302, "INVALID_BRAND", "Please set a valid brand"),
  INVALID_ID(303, "INVALID_ID", "Please set a valid id"),
  DEVICE_NOT_FOUND(311, "DEVICE_NOT_FOUND", "Requested device wasn't found"),
  UNKNOWN_ERROR(999, "UNKNOWN_ERROR", "");

  /**
   * Error code associated to specific
   */
  private int code;

  /**
   * Key to identify which exception is
   */
  private String key;

  /**
   * Message associated to specific Exception
   */
  private String msg;

  /**
   * Constructor
   * 
   * @param aCode
   * @param aKey
   * @param aMsg
   */
  private ExceptionEnum(int aCode, String aKey, String aMsg) {
    code = aCode;
    key = aKey;
    msg = aMsg;
  }
}
