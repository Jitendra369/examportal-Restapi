package com.exam.service;

import java.util.Set;

import com.exam.models.exam.Category;

// DAO layer for crud-operations 
public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getAllCategories();
	
	public Category getCategory(int cateId);
	
	public void deleteCategory(int catId);

}
