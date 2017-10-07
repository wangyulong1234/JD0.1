package com.lanqiao;

//һ������
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//�����̳�HttpServlet��������࣬��дdoGet() �� doPost()
public class HelloServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		System.out.println("doGet");
		//���������ͻ���д��ʱ������
		//����������ڵ�һ�����response.setContentType("");
		//���ã�1.���÷�������Ӧ�ı��뷽ʽΪutf-8
		//2.��ʾ�ͻ��������ʹ��utf-8����
		response.setContentType("text/html;charset=utf-8");
		
		request.setCharacterEncoding("utf-8");
		
		String usrname = request.getParameter("username");
//		System.out.println(usrname);
		
		PrintWriter pw = response.getWriter();
		pw.println("���A!");
		
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
