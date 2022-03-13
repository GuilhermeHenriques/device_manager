package com.exercise.device.handlers;

import com.exercise.device.exceptions.DeviceException;
import com.exercise.device.exceptions.ExceptionEnum;
import com.exercise.device.models.ExceptionModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public abstract class CommonHandler<I, O> {
  /**
   * Logger for all handlers
   */
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * Status code associated to specific request
   */
  private HttpStatus status;

  /**
   * Output associated to a specific request
   */
  private Object output = null;

  /**
   * Execute handler
   * 
   * @return
   */
  public CommonHandler<I, O> execute(I aInput) {
    try {
      setOutput(handleRequest(aInput), HttpStatus.OK);
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

  private ExceptionModel handleFail(Exception e) {
    DeviceException result = null;

    if (e instanceof DeviceException) {
      result = (DeviceException) e;
    } else {
      result = new DeviceException();
      result.setMsg(e.getMessage());
    }

    logger.error(result.getMsg(), e);

    return new ExceptionModel(result.getCode(), result.getKey(), result.getMsg(), result.getStackTrace());
  }

  /**
   * Validate a device id
   * 
   * @param aId
   * @throws DeviceException
   */
  protected void checkId(Integer aId) throws DeviceException {
    if (aId == null || aId != null && aId <= 0) {
      throw new DeviceException(ExceptionEnum.INVALID_ID);
    }
  }
}
