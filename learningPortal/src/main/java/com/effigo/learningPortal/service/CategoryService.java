package com.effigo.learningPortal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.effigo.learningPortal.dto.CategoryDto;
import com.effigo.learningPortal.entity.Category;
import com.effigo.learningPortal.mapper.CourseMapper;
import com.effigo.learningPortal.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

	private final CategoryRepository categoryRepository;

	private final CourseMapper courseMapper;

	public CategoryService(CategoryRepository categoryRepository, CourseMapper courseMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.courseMapper = courseMapper;
	}

	public boolean addCategory(CategoryDto categoryDto) {

		try {
			Category toAdd = courseMapper.categoryDtoToCategory(categoryDto);
			if (toAdd == null) {
				log.error("Failed to add category. Received null Category object from mapper.");
				return false;
			}
			categoryRepository.save(toAdd);
			log.info("Category added successfully: {}", toAdd);
			return true;

		} catch (Exception e) {
			log.error("Failed to add category. Category already exists.", e);
			return false;
		}
	}

	public Map<String, List<Category>> getCategory() {
		Map<String, List<Category>> response = new HashMap<>();
		response.put("Message", categoryRepository.findAll());
		return response;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}