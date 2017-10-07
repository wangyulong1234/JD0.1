package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.CategoryServiceImpl;
import com.service.inter.CategoryService;
import com.vo.Category;

public class CategoryServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String target = "";
		//һ.�������
		String cname = request.getParameter("cname");
		String cdesc = request.getParameter("cdesc");
		System.out.println(cname);
		System.out.println(cdesc);
		//��.����ҵ���߼�
		Category category = new Category();
		category.setCname(cname);
		category.setCdesc(cdesc);
		
		CategoryService service = new CategoryServiceImpl();
		try {
			service.addCategory(category);
			request.setAttribute("msg", "���һ����Ʒ����ɹ�");
		} catch (Exception e) {
			request.setAttribute("msg", "���һ����Ʒ����ʧ��");
			e.printStackTrace();
		}
		//��.ת����ͼ
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);
		
		rd.forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
