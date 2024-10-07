package com.hzn.hutils;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 4.
 */
public class RCH {

	public static HttpServletRequest getRequest () {
		return RequestContextHolder.getRequest ();
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


	@WebListener
	public static class RequestContextHolder implements ServletRequestListener {
		private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<> ();

		@Override
		public void requestInitialized (ServletRequestEvent sre) {
			requestHolder.set ((HttpServletRequest) sre.getServletRequest ());
		}

		@Override
		public void requestDestroyed (ServletRequestEvent sre) {
			requestHolder.remove ();
		}

		public static HttpServletRequest getRequest () {
			return requestHolder.get ();
		}
	}
}
