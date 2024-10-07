package com.hzn.hutils;

import com.hzn.hutils.filter.ResponseHolder;
import com.hzn.hutils.weblistener.RequestHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * <p>Request Context Holder</p>
 *
 * @author hzn
 * @date 2024. 10. 4.
 */
public class Rch {

	public static HttpServletRequest getRequest () {
		return RequestHolder.getRequest ();
	}

	public static HttpSession getSession () {
		return getRequest ().getSession ();
	}

	public static void setRequestAttribute (String name, Object value) {
		getRequest ().setAttribute (name, value);
	}

	public static Object getRequestAttribute (String name) {
		return getRequest ().getAttribute (name);
	}

	public static void removeRequestAttribute (String name) {
		getRequest ().removeAttribute (name);
	}

	public static void setSessionAttribute (String name, Object value) {
		getSession ().setAttribute (name, value);
	}

	public static Object getSessionAttribute (String name) {
		return getSession ().getAttribute (name);
	}

	public static void removeSessionAttribute (String name) {
		getSession ().removeAttribute (name);
	}

	public HttpServletResponse getResponse () {
		return ResponseHolder.getResponse ();
	}
}
