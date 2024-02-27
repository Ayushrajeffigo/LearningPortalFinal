package com.effigo.learningPortal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.effigo.learningPortal.dto.CategoryDto;
import com.effigo.learningPortal.dto.CourseDto;
import com.effigo.learningPortal.entity.Category;
import com.effigo.learningPortal.entity.Courses;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	@Mapping(target = "id", ignore = true)
	Category categoryDtoToCategory(CategoryDto categoryDto);

	/**
	 * @param courseDto
	 * @return
	 */
	Courses courseDtoToCourses(CourseDto courseDto);

	/**
	 * @param type
	 * @return
	 */
	Category typeToCategory(String type);

	CategoryDto categoryToCategoryDto(Category category);
}