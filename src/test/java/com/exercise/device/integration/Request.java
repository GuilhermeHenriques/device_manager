package com.exercise.device.integration;

import com.exercise.device.ApplicationTests;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class Request extends ApplicationTests {
  /**
   * Logger class
   */
  private static final Logger logger = LoggerFactory.getLogger(Request.class);

  /**
   * Mock object to create a request
   */
  @Autowired
  private MockMvc mock;

  /**
   * Response from specific request
   */
  private MockHttpServletResponse response;

  /**
   * Make specific request and load the response into memory
   * 
   * @param aBuilder
   * @param aBody
   */
  private void request(MockHttpServletRequestBuilder aBuilder, Object aBody) {

    try {
      if (aBody != null) {
        String bodyStr = new Gson().toJson(aBody);
        aBuilder.content(bodyStr);
      }

      aBuilder.contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON);

      response = mock.perform(aBuilder).andReturn().getResponse();
    } catch (Exception e) {
      logger.error("request failed:", e);
    }
  }

  /**
   * Make GET request
   * 
   * @param aUrl
   * @return
   */
  protected Request get(String aUrl) {
    request(MockMvcRequestBuilders.get(aUrl), null);
    return this;
  }

  /**
   * Make POST request
   * 
   * @param aUrl
   * @param aBody
   * @return
   */
  protected Request post(String aUrl, Object aBody) {
    request(MockMvcRequestBuilders.post(aUrl), aBody);
    return this;
  }

  /**
   * Make PUT request
   */
  protected Request put(String aUrl, Object aBody) {
    request(MockMvcRequestBuilders.put(aUrl), aBody);
    return this;
  }

  /**
   * Make DELETE request
   * 
   * @param aUrl
   * @param aBody
   * @return
   */
  protected Request delete(String aUrl, Object aBody) {
    request(MockMvcRequestBuilders.put(aUrl), aBody);
    return this;
  }

  /**
   * Get response object
   * 
   * @return
   */
  public MockHttpServletResponse getResponse() {
    return response;
  }

  /**
   * Get response as string
   * 
   * @return
   */
  protected String asString() {
    String result = "";

    try {
      result = getResponse().getContentAsString();
    } catch (Exception e) {
      logger.error("It wasn't possible to get response as String", e);
    }

    return result;
  }
}
