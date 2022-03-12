package com.exercise.device.controllers;

import com.exercise.device.handlers.CommonHandler;

import org.springframework.http.ResponseEntity;

public abstract class BaseController {
  /**
   * Request execution
   * 
   * @param aHandler
   * @return
   */
  protected ResponseEntity<Object> response(CommonHandler<?, ?> aHandler) {
    return new ResponseEntity<Object>(aHandler.getOutput(), aHandler.getStatus());
  }
}
