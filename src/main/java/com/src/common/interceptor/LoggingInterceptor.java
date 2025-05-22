package com.src.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		
		System.out.println("[Request] Method: " + method);
		System.out.println("[Request] URI: " + uri);
		if (queryString != null) {
			System.out.println("[Request] Query String: " + queryString);
		}
		
		if ("POST".equalsIgnoreCase(method)) {
			// POST 파라미터 로그 출력 (form-data나 x-www-form-urlencoded만 해당)
			request.getParameterMap().forEach((key, values) -> {
				for (String value : values) {
					System.out.println("[POST Param] " + key + " = " + value);
				}
			});
		}
		return true;
	}
}
