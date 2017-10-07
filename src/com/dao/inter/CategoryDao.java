package com.dao.inter;

import java.util.List;

import com.vo.Category;

public interface CategoryDao {

	//1.添加
	public int addCategory(Category category) throws Exception;
	//2.删除
	public int deleteCategory(Category category);
	//3.修改
	public int updateCategory(Category category);
	//4.查1
	public Category getCategoryById(int id);
	//5.按SQL语句查
	public List<Category> getPageByQuery(String sql);
}
