package com.exam.controller;

import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.impl.CategoryServiceImpl;
import com.exam.models.exam.Category;
import com.exam.repo.CategoryRepo;
import com.exam.service.CategoryService;

@RestController
@RequestMapping("/catagery")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
//	add categ.
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category) {
		Category cate = this.categoryServiceImpl.addCategory(category);
		return ResponseEntity.ok(cate);
	}
	
//	get single catg
	@GetMapping("/{catId}")
	public ResponseEntity<?> getCategory(@PathVariable("catId") int catId){
		Category category = this.categoryServiceImpl.getCategory(catId);
		return ResponseEntity.ok(category);
	}
	
//	update categ, get old id & then update the  categ.. otherwise override happen
//	if id is not present it will create the new Categ. we need to use logic here
	@PutMapping("/")
	public ResponseEntity<?> updateCateg(@RequestBody Category category) {
		Category updateCategory = this.categoryServiceImpl.updateCategory(category);
		return ResponseEntity.ok(updateCategory);
	}
	
//	get All categ.
	@GetMapping("/")
	public ResponseEntity<?> getAllCateg(){
		Set<Category> allCategories = this.categoryServiceImpl.getAllCategories();
		return ResponseEntity.ok(allCategories);
	}
	
//	delete single categ.
	@DeleteMapping("/{catId}")
	public ResponseEntity<?> deleteCateg(@PathVariable("catId") int catId) {
		this.categoryServiceImpl.deleteCategory(catId);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}
	
	
}
