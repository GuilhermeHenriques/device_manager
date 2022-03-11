package com.exercise.device.handlers;

import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.models.ExceptionResponse;

import org.springframework.http.HttpStatus;

public abstract class CommonHandler<I, O> {
  /**
   * Status code associated to specific request
   */
  private HttpStatus status;

  /**
   * Input associated to a specific request
   */
  private I input;

  /**
   * Output associated to a specific request
   */
  private Object output = null;

  /**
   * Constructor
   */
  public CommonHandler(I aInput) {
    input = aInput;
  }

  public CommonHandler<I, O> execute() {
    try {
      setOutput(handleRequest(input), HttpStatus.OK);
    } catch (Exception e) {
      setOutput(handleFail(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return this;
  }

  /**
   * Set output to memory
   * 
   * @param aOutput
   * @param aStatus
   */
  private void setOutput(Object aOutput, HttpStatus aStatus) {
    output = aOutput;
    status = aStatus;
  }

  /**
   * @return the output
   */
  public Object getOutput() {
    return output;
  }

  /**
   * @return the status
   */
  public HttpStatus getStatus() {
    return status;
  }

  /**
   * Must be implemented in every handlers
   * 
   * @param aInput
   * @return
   */
  protected abstract O handleRequest(I aInput) throws Exception;

  private ExceptionResponse handleFail(Exception e) {
    DeviceException result = null;

    if (e instanceof DeviceException) {
      result = (DeviceException) e;
    } else {
      result = new DeviceException();
      result.setMsg(e.getMessage());
    }

    return new ExceptionResponse(result.getCode(), result.getKey(), result.getMsg(), result.getStackTrace());
  }
}
