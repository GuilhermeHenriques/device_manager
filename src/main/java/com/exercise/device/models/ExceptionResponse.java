package com.exercise.device.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
  private int code;
  private String key;
  private String msg;
  private StackTraceElement[] trace;
}
