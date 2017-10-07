package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	//字符编码集过滤器
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("CharacterFilter前置执行1");
		//一.转换成带http的
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//二.设置编码集
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		//把请求交给下一个过滤器 如果后来没有过滤器 那么执行servlet的doGet()或doPost()方法
		chain.doFilter(req, resp);
		System.out.println("CharacterFilter后置执行2");
	}

	@Override
	public void destroy() {
		
	}

}
