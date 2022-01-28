package com.miex.user.config;

import com.miex.user.domain.LocalHeader;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liutz
 * @since 2022/1/25
 */
@Component
public class AllRequestHandlerInterceptor implements HandlerInterceptor {
	public static ThreadLocal<LocalHeader> threadLocal = new ThreadLocal<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LocalHeader header = new LocalHeader();
		header.setRequestId(request.getHeader("requestId"));
		threadLocal.set(header);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
								@Nullable Exception ex) throws Exception {
		threadLocal.remove();
	}
}
