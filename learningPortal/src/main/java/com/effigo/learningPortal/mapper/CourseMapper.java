package com.effigo.learningPortal.mapper;

import org.mapstruct.Mapper;

import com.effigo.learningPortal.dto.CategoryDto;
import com.effigo.learningPortal.dto.CourseDto;
import com.effigo.learningPortal.entity.Category;
import com.effigo.learningPortal.entity.Courses;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	Category categoryDtoToCategory(CategoryDto categoryDto);

	Courses courseDtoToCourses(CourseDto courseDto);

	Category typeToCategory(String type);

	CategoryDto categoryToCategoryDto(Category category);
}