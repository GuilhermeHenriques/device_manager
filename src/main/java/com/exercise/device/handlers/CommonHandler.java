package com.exercise.device.handlers;

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
  private O output = null;

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
      setOutput(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return this;
  }

  /**
   * Set output to memory
   * 
   * @param aOutput
   */
  private void setOutput(O aOutput, HttpStatus aStatus) {
    output = aOutput;
    status = aStatus;
  }

  /**
   * @return the output
   */
  public O getOutput() {
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
  protected abstract O handleRequest(I aInput);

}
