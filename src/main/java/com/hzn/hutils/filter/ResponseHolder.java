package com.hzn.hutils.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 7.
 */
@WebFilter (urlPatterns = "/*")
public class ResponseHolder implements Filter {
	private static final ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<> ();

	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			responseHolder.set ((HttpServletResponse) response);
			chain.doFilter (request, response);
		} finally {
			responseHolder.remove ();
		}
	}

	public static HttpServletResponse getResponse () {
		return responseHolder.get ();
	}

	@Override
	public void init (FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy () {
	}
}
