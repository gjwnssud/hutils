package com.hzn.hutils.http;

import com.hzn.hutils.http.HttpClient.Headers;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 4.
 */
public class HttpResponse<T> {

  private final int code;
  private final String message;
  private final T data;
  private final Headers responseHeaders;

  private HttpResponse(int code, String message, T data, Headers responseHeaders) {
    this.code = code;
    this.message = message;
    this.data = data;
    this.responseHeaders = responseHeaders;
  }

  public static <T> HttpResponse<T> of(int code, String message) {
    return of(code, message, null);
  }

  public static <T> HttpResponse<T> of(int code, String message, Headers responseHeaders) {
    return of(code, message, null, responseHeaders);
  }

  public static <T> HttpResponse<T> of(T responseBody) {
    return of(HttpStatus.OK, responseBody);
  }

  public static <T> HttpResponse<T> of(T responseBody, Headers responseHeaders) {
    return of(HttpStatus.OK, responseBody, responseHeaders);
  }

  public static <T> HttpResponse<T> of(HttpStatus status) {
    return of(status.getCode(), status.getMessage(), null, null);
  }

  public static <T> HttpResponse<T> of(HttpStatus status, Headers responseHeaders) {
    return of(status.getCode(), status.getMessage(), null, responseHeaders);
  }

  public static <T> HttpResponse<T> of(HttpStatus status, T data) {
    return of(status.getCode(), status.getMessage(), data, null);
  }

  public static <T> HttpResponse<T> of(HttpStatus status, T data, Headers responseHeaders) {
    return of(status.getCode(), status.getMessage(), data, responseHeaders);
  }

  public static <T> HttpResponse<T> of(int code, String message, T data,
      Headers responseHeaders) {
    return new HttpResponse<>(code, message, data, responseHeaders);
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public Headers getResponseHeaders() {
    return responseHeaders;
  }
}
