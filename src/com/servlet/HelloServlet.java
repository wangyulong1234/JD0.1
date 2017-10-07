package com.servlet;

//一.导包
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//二.继承HttpServlet这个抽象类  重写doGet()和doPost
public class HelloServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//二.处理客户端发给服务器时,服务器接受到以后乱码
		
		//提示tomcat服务器 使用utf-8解码 request中的数据
		//只对post提交方式有效
		request.setCharacterEncoding("utf-8");
		
		System.out.println("in doGet");
		response.setContentType("text/html;charset=utf-8");
		
		
		//String username = request.getParameter("username");
		
		
		PrintWriter pw = response.getWriter();

		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>我是标题</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("当前时间" + new Date());
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
