package com.servlet;

//һ.����
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//��.�̳�HttpServlet���������  ��дdoGet()��doPost
public class HelloServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//��.����ͻ��˷���������ʱ,���������ܵ��Ժ�����
		
		//��ʾtomcat������ ʹ��utf-8���� request�е�����
		//ֻ��post�ύ��ʽ��Ч
		request.setCharacterEncoding("utf-8");
		
		System.out.println("in doGet");
		response.setContentType("text/html;charset=utf-8");
		
		
		//String username = request.getParameter("username");
		
		
		PrintWriter pw = response.getWriter();

		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>���Ǳ���</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("��ǰʱ��" + new Date());
		pw.println("</body>");
		pw.println("</html>");
		pw.flush();
		pw.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		this.doGet(request, response);
	}



}
