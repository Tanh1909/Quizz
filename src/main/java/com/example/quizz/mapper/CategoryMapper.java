package com.example.quizz.mapper;

import com.example.quizz.dto.response.CategoryResponse;
import com.example.quizz.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    @Mapping(target ="topics",ignore = true )
    Category toCategory(CategoryResponse categoryResponse);

    List<Category> toCategories(List<CategoryResponse> categoryResponses);
}
