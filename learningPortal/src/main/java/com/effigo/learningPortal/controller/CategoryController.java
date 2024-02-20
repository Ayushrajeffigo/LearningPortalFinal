package com.effigo.learningPortal.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.learningPortal.dto.CategoryDto;
import com.effigo.learningPortal.entity.Category;
import com.effigo.learningPortal.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/create")
	public ResponseEntity<HashMap<String, String>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		boolean add = categoryService.addCategory(categoryDto);
		HashMap<String, String> response = new HashMap<>();

		if (add) {
			response.put("Message", "Category Added.");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.put("Message", "Something went wrong");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<HashMap<String, List<Category>>> getAllCategory() {
		List<Category> allCategories = categoryService.getAllCategories(); // Assuming you have a method to get all
																			// categories from the service
		HashMap<String, List<Category>> response = new HashMap<>();

		response.put("categories", allCategories);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}