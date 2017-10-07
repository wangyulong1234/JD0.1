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
		//一.填充数据
		String cname = request.getParameter("cname");
		String cdesc = request.getParameter("cdesc");
		System.out.println(cname);
		System.out.println(cdesc);
		//二.调用业务逻辑
		Category category = new Category();
		category.setCname(cname);
		category.setCdesc(cdesc);
		
		CategoryService service = new CategoryServiceImpl();
		try {
			service.addCategory(category);
			request.setAttribute("msg", "添加一级商品种类成功");
		} catch (Exception e) {
			request.setAttribute("msg", "添加一级商品种类失败");
			e.printStackTrace();
		}
		//三.转发视图
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);
		
		rd.forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
