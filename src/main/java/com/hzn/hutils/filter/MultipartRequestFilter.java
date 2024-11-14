package com.hzn.hutils.filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 11. 15.
 */
@WebFilter (filterName = "MultipartRequestFilter", urlPatterns = "/**")
public class MultipartRequestFilter implements Filter {
	public static final ThreadLocal<Map<MultipartFile, File>> MULTIPART_THREAD_LOCAL = new ThreadLocal<> ();

	@Override
	public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		if (servletRequest.getDispatcherType () == DispatcherType.REQUEST) {
			filterChain.doFilter (servletRequest, servletResponse);
			if (servletRequest instanceof MultipartHttpServletRequest) {
				// 요청이 끝난 후 임시로 생성된 파일을 삭제한다.
				Map<MultipartFile, File> multipartFileMap = MULTIPART_THREAD_LOCAL.get ();
				if (multipartFileMap != null) {
					multipartFileMap.forEach ((mf, f) -> {
						if (f.exists ()) {
							f.delete ();
						}
					});
				}
				MULTIPART_THREAD_LOCAL.remove ();
			}
		}
	}
}
