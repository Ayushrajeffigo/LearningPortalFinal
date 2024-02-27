package com.effigo.learningPortal.mapper;

import com.effigo.learningPortal.dto.CategoryDto;
import com.effigo.learningPortal.dto.CourseDto;
import com.effigo.learningPortal.entity.Category;
import com.effigo.learningPortal.entity.Courses;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-26T15:23:47+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryType( categoryDto.getCategoryType() );

        return category;
    }

    @Override
    public Courses courseDtoToCourses(CourseDto courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Courses courses = new Courses();

        courses.setCourseName( courseDto.getCourseName() );

        return courses;
    }

    @Override
    public Category typeToCategory(String type) {
        if ( type == null ) {
            return null;
        }

        Category category = new Category();

        return category;
    }

    @Override
    public CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryType( category.getCategoryType() );

        return categoryDto;
    }
}
