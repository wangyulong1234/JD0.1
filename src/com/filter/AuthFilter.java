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

public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	//�ַ����뼯������
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("AuthFilterǰ��ִ��4");
		//һ.ת���ɴ�http��
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//Ȩ����֤����
		
		//�����󽻸���һ�������� �������û�й����� ��ôִ��servlet��doGet()��doPost()����
		chain.doFilter(req, resp);
		System.out.println("AuthFilter����ִ��5");
	}

	@Override
	public void destroy() {
		
	}

}
