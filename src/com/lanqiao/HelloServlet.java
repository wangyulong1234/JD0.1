package com.lanqiao;

//一、导包
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//二、继承HttpServlet这个抽象类，重写doGet() 和 doPost()
public class HelloServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		System.out.println("doGet");
		//服务器给客户端写的时候乱码
		//解决方法：在第一句加入response.setContentType("");
		//作用：1.设置服务器响应的编码方式为utf-8
		//2.提示客户端浏览器使用utf-8解码
		response.setContentType("text/html;charset=utf-8");
		
		request.setCharacterEncoding("utf-8");
		
		String usrname = request.getParameter("username");
//		System.out.println(usrname);
		
		PrintWriter pw = response.getWriter();
		pw.println("你好A!");
		
		pw.flush();
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		System.out.println("doPost");
		this.doGet(request, response);
	}
}
