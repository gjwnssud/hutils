package com.hzn.hutils.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 11. 15.
 */
@Component
@WebFilter (filterName = "MultipartRequestFilter", urlPatterns = "/**")
@Order
public class MultipartRequestFilter extends OncePerRequestFilter {
	public static final ThreadLocal<Map<MultipartFile, File>> MULTIPART_THREAD_LOCAL = new ThreadLocal<> ();

	@Override
	protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		filterChain.doFilter (request, response);
		if (request instanceof MultipartHttpServletRequest) {
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
