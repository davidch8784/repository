package com.samsung.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	FilterConfig config;
	
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 서블릿을 실행하기 전에 해야 할 일들
		// 암호 해제, 압축 해제 등의 일
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		
		chain.doFilter(request, response);
		
		// 서블릿 실행 후 해야 할 일들
		// 압축, 암호화 등등...
	}
	
	public void destroy() {}



}
