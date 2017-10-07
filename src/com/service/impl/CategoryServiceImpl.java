package com.service.impl;

import com.dao.impl.CategoryDaoImpl;
import com.dao.inter.CategoryDao;
import com.service.inter.CategoryService;
import com.vo.Category;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDao dao;
	
	public CategoryServiceImpl(){
		dao = new CategoryDaoImpl();
	}
	
	@Override
	public int addCategory(Category category) throws Exception{
		
		int count = dao.addCategory(category);
		return count;
	}

}
