package com.hzn.hutils.weblistener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 7.
 */
@WebListener
public class RequestHolder implements ServletRequestListener {
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
