package com.exam.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.exam.models.exam.Category;
import com.exam.repo.CategoryRepo;
import com.exam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) {
		return this.categoryRepo.save(category);
	}

//	handle by put method
	@Override
	public Category updateCategory(Category category) {
		return this.categoryRepo.save(category);
	}

	@Override
	public Set<Category> getAllCategories() {
		return new LinkedHashSet<>(this.categoryRepo.findAll());
	}

	@Override
	public Category getCategory(int cateId) {
//		we use get, because it return optional()
		return this.categoryRepo.findById(cateId).get();
		
	}

	@Override
	public void deleteCategory(int catId) {
		this.categoryRepo.deleteById(catId);
	}

}
