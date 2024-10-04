package com.hzn.util.http;

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

	private HttpResponse (int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode () {
		return code;
	}

	public String getMessage () {
		return message;
	}

	public T getData () {
		return data;
	}

	public static <T> HttpResponse<T> of (int code, String message) {
		return of (code, message, null);
	}

	public static <T> HttpResponse<T> of (T responseBody) {
		return of (HttpStatus.OK, responseBody);
	}

	public static <T> HttpResponse<T> of (HttpStatus status) {
		return of (status.getCode (), status.getMessage (), null);
	}

	public static <T> HttpResponse<T> of (HttpStatus status, T data) {
		return of (status.getCode (), status.getMessage (), data);
	}

	public static <T> HttpResponse<T> of (int code, String message, T data) {
		return new HttpResponse<> (code, message, data);
	}
}
