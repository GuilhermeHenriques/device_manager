package com.exercise.device.controllers.v1;

import com.exercise.device.handlers.CommonHandler;

import org.springframework.http.ResponseEntity;

public abstract class ApiV1 {
  /**
   * Request execution
   * 
   * @param aHandler
   * @return
   */
  protected ResponseEntity<Object> response(CommonHandler<?, ?> aHandler) {
    aHandler.execute();

    return new ResponseEntity<Object>(aHandler.getOutput(), aHandler.getStatus());
  }
}
